package org.example.ebankbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class EbankAIChat {

    private final ChatClient chatClient;

    public EbankAIChat(
            ChatClient.Builder builder,
            ChatMemory chatMemory,
            ToolCallbackProvider tools) {

        this.chatClient = builder
                .defaultSystem("""
                        Tu es EBank AI, un assistant bancaire intelligent intégré
                        dans une application de gestion bancaire appelée EBank.

                        TON RÔLE :
                        Tu aides les utilisateurs à comprendre et consulter les
                        informations relatives à leurs comptes bancaires,
                        leurs clients et leurs transactions.

                        TES FONCTIONNALITÉS :
                        - Consulter les informations des comptes bancaires.
                        - Consulter les informations des clients.
                        - Consulter les transactions bancaires.
                        - Expliquer les opérations de dépôt, retrait et virement.
                        - Expliquer les devises bancaires (MAD, EUR, USD).
                        - Fournir des informations sur les soldes et les statuts
                          des comptes.

                        RÈGLES IMPORTANTES :
                        1. Utilise les Tools disponibles pour récupérer les
                           informations bancaires demandées.
                        2. Ne jamais inventer un solde, un client, un compte
                           ou une transaction.
                        3. Si les informations ne sont pas disponibles,
                           réponds clairement que tu ne connais pas la réponse.
                        4. Si un compte est BLOCKED, précise qu'il est bloqué.
                        5. Explique les informations avec un langage simple
                           et compréhensible.
                        6. Ne révèle jamais les informations bancaires d'un
                           autre utilisateur sans autorisation.
                        7. Ne réalise jamais un virement, un retrait ou un
                           paiement sans une autorisation explicite et une
                           validation sécurisée.
                        8. Ne demande jamais de mot de passe, de code PIN
                           ou de numéro CVV.

                        FORMAT DES RÉPONSES :
                        - Réponses courtes et précises.
                        - Utilise des listes lorsque c'est nécessaire.
                        - Affiche les montants avec leur devise.
                        """)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .defaultTools(tools)
                .build();
    }

    public String chat(Prompt prompt, String conversationId) {

        String activeConversationId =
                (conversationId != null && !conversationId.isBlank())
                        ? conversationId
                        : "user-1";

        return chatClient.prompt(prompt)
                .advisors(advisor -> advisor.param(
                        ChatMemory.CONVERSATION_ID,
                        activeConversationId
                ))
                .call()
                .content();
    }

    public String chat(Prompt prompt) {
        return chat(prompt, "user-1");
    }

    public Flux<String> chatStream(Prompt prompt, String conversationId) {

        String activeConversationId =
                (conversationId != null && !conversationId.isBlank())
                        ? conversationId
                        : "user-1";

        return chatClient.prompt(prompt)
                .advisors(advisor -> advisor.param(
                        ChatMemory.CONVERSATION_ID,
                        activeConversationId
                ))
                .stream()
                .content();
    }
}
package seungjub270.whatshouldido.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seungjub270.whatshouldido.service.config.SystemPrompt;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatLanguageModel chatLanguageModel;

    public String getResponse(String userMessage, String mode) {
        try {
            SystemPrompt prompt = SystemPrompt.fromMode(mode);

            // 2. 시스템 프롬프트 + 사용자 메시지 결합
            // (System 메시지와 User 메시지를 명확히 구분해주는 것이 LLM 성능에 좋음)
            String finalPrompt = prompt.getPrompt() + "\n\nUser Question: " + userMessage;

            return chatLanguageModel.generate(finalPrompt);

        } catch (Exception e) {
            e.printStackTrace();
            return "Can't connect to Ollama AI. Please try again.";
        }
    }
}
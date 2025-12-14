package seungjub270.whatshouldido.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import seungjub270.whatshouldido.service.ChatService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class Chat {

    // private final ChatService chatService; // 기존 규칙 기반 서비스 주석 처리
    private final ChatService chatService; // 새로 만든 AI 서비스 주입

    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }

    @PostMapping("/api/chat/send")
    @ResponseBody
    public Map<String, String> sendMessage(@RequestBody Map<String, String> payload, @RequestParam String mode) {
        String userMessage = payload.get("message");

        // AI에게 질문하고 답 받아오기
        String botResponse = chatService.getResponse(userMessage, mode);

        return Map.of("response", botResponse);
    }
}
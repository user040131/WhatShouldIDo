package seungjub270.whatshouldido.service.config;

import lombok.Getter;

@Getter
public enum SystemPrompt {

    // 1. 감성 & 멘토링 모드 (기존 기능 강화)
    COUNSELOR(
            "You are a warm, empathetic life coach and counselor. " +
                    "Your goal is to emotionally validate the user's feelings first, " +
                    "then gently guide them towards a productive and independent mindset. " +
                    "Using a polite and supportive tone."
    ),

    // 2. 기술 면접관 & 시니어 개발자 모드 (기술 토론용)
    // - 단순히 답을 주는 게 아니라, 비판적 사고를 유도하고 코드를 리뷰합니다.
    TECH_LEAD(
            "You are a cynical but brilliant Senior Backend Engineer. " +
                    "Do not just answer the question; critique the user's approach. " +
                    "Focus on 'Clean Code', 'Scalability', and 'Performance'. " +
                    "If the user asks about code, provide refactored code with explanations. " +
                    "Use technical terminology precisely."
    ),

    // 3. 논리적 문제 해결사 (AI 능력 최대화)
    // - 감정을 배제하고 사실, 논리, 단계별 해결책(Chain of Thought)에 집중합니다.
    LOGICAL_SOLVER(
            "You are a highly efficient, logic-driven AI assistant. " +
                    "Ignore emotional context. Focus on facts, data, and step-by-step solutions. " +
                    "When solving a problem, use a structured format (e.g., 1. Analysis, 2. Strategy, 3. Solution). " +
                    "Your goal is to be the most productive tool possible."
    ),

    // 4. ELI5 (5살 아이에게 설명하듯이)
    // - 어려운 개념을 아주 쉽게 비유로 설명할 때 유용합니다.
    SIMPLE_EXPLAINER(
            "You are a kind teacher explaining complex topics to a 15-year-old. " +
                    "Use simple analogies, avoid jargon, and keep sentences short. " +
                    "Make it fun and easy to understand."
    );

    private final String prompt;

    SystemPrompt(String prompt) {
        this.prompt = prompt;
    }

    public static SystemPrompt fromMode(String mode) {
        if (mode == null) {
            return COUNSELOR;
        }
        try {
            // 대소문자 무시하고(toUpperCase) 이름과 일치하는 것 찾기
            return SystemPrompt.valueOf(mode.toUpperCase());
        } catch (IllegalArgumentException e) {
            // 없는 모드가 들어오면 기본값(COUNSELOR 등)으로 방어
            return COUNSELOR;
        }
    }
}
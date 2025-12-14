package seungjub270.whatshouldido.controller;

import lombok.RequiredArgsConstructor; // AllArgsConstructor 대신 이거 추천 (final 필드만 생성자)
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import seungjub270.whatshouldido.service.FoodService;

@Controller
@RequiredArgsConstructor
public class Food {

    private final FoodService foodService;

    // 1. 음식 추천 페이지로 이동 (메인에서 버튼 눌렀을 때)
    @GetMapping("/food")
    public String foodPage() {
        return "food"; // templates/food.html
    }

    // 2. 결과 뽑기 로직
    @PostMapping("/food/random")
    public String random(@RequestParam(required = false) String time, Model model) {
        String picked = foodService.randomFood(time);
        model.addAttribute("food", picked);
        return "food";
    }
}
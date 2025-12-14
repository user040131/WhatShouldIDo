package seungjub270.whatshouldido.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import seungjub270.whatshouldido.service.LottoService;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class Lotto {
    private final LottoService lottoService;
    // 1. 음식 추천 페이지로 이동 (메인에서 버튼 눌렀을 때)
    @GetMapping("/lotto")
    public String lottoPage() {
        return "lotto";
    }

    // 2. 결과 뽑기 로직
    @PostMapping("/lotto/random")
    public String random(Model model) {
        Set lotto = lottoService.lottoNum();
        model.addAttribute("lotto", lotto);
        return "lotto";
    }
}

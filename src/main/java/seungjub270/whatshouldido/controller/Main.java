package seungjub270.whatshouldido.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Main {

    @GetMapping("/")
    public String main() {
        return "index";
    } //추후에 음식 외에도 다른 랜덤 요소 추가할 수도 있기에 만들어둠
}

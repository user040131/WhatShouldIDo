package seungjub270.whatshouldido.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Sphere {

    @GetMapping("/sphere")
    public String spherePage() {
        return "sphere";
    }
}

package com.bht.dimage.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class BetaController {

    @GetMapping("/account")
    public String accountPage() {
        return "account";
    }
}

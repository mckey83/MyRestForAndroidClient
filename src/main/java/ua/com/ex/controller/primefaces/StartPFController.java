package ua.com.ex.controller.primefaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartPFController {
    @RequestMapping("/")
    public String home() {
        return "templates/masterLayout.xhtml";
    }

}
package ua.com.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {
    @RequestMapping("/")
    public String home() {
        return "templates/masterLayout.xhtml";
    }

}
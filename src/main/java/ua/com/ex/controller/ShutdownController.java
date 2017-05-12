package ua.com.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shutdown")
public class ShutdownController {

    @Autowired
    private ApplicationContext appContext;

    @GetMapping("/start")
    public void shutdown() {
        ((ConfigurableApplicationContext) appContext).close();    
        System.exit(0);
    }

}




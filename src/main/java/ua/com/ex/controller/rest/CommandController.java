package ua.com.ex.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/command")
public class CommandController {

    @Autowired
    private ApplicationContext appContext;   

    @GetMapping("/shutdown/start")
    public void shutdown() {
        ((ConfigurableApplicationContext) appContext).close();    
        System.exit(0);
    }
    
    @GetMapping("/restart")
    public void restart() {
        ((ConfigurableApplicationContext) appContext).refresh();
    }   
   
}




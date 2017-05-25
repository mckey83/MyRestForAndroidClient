package ua.com.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.ex.service.RemoteDataService;

@RestController
@RequestMapping("/command")
public class CommandController {

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private RemoteDataService remoteDataService;   

    @GetMapping("/shutdown/start")
    public void shutdown() {
        ((ConfigurableApplicationContext) appContext).close();    
        System.exit(0);
    }

    @GetMapping("/update")
    public String update() {
        return remoteDataService.updateFromEx();
    }

    @GetMapping("/restart")
    public void restart() {
        ((ConfigurableApplicationContext) appContext).refresh();
    }   
   
}




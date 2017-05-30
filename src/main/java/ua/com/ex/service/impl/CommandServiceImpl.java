package ua.com.ex.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import ua.com.ex.service.interfaces.CommandService;

@Service("commandService")
public class CommandServiceImpl implements CommandService {

    @Autowired
    private ApplicationContext appContext;

    @Override
    public void shutdown() {
        ((ConfigurableApplicationContext) appContext).close();    
        System.exit(0);
    }  


}

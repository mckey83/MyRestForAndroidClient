package ua.com.ex.controller.primefaces;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ua.com.ex.service.interfaces.CommandService;
import ua.com.ex.service.interfaces.RemoteDataService;

@Getter
@Setter
@Controller
@ViewScoped
@ManagedBean
public class CommandPFController implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(CommandPFController.class); 
    
    @Autowired
    private RemoteDataService  remoteDataService;
    
    @Autowired
    private CommandService commandService;   

    public void update() {  
        long start = System.currentTimeMillis();             
        try {
            remoteDataService.updateData();            
        } catch (Exception e) {
            logger.error("error load resource", e);
        }         
        long elapsedTimeMillis = System.currentTimeMillis()-start;        
        logger.info("CommandPFController.update() time "+elapsedTimeMillis/1000F+ " s ");
    }     
    
    public void shutdown(){
        commandService.shutdown();
    }
}

package ua.com.ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ua.com.ex.exception.ServiceException;
import ua.com.ex.service.impl.RemoteDataServiceImpl;
import ua.com.ex.service.interfaces.RemoteDataService;

@SpringBootApplication(scanBasePackages = { "ua.com.ex" })
public class Rest {
    
    private static final Logger logger = LoggerFactory.getLogger(Rest.class);
 
	public static void main(String[] args) { 	    
	    ApplicationContext app = SpringApplication.run(Rest.class, args);	    
	     
	     RemoteDataService myBean = app.getBean(RemoteDataServiceImpl.class);//get the bean by type
	     try {
            myBean.updateData();
        } catch (ServiceException e) {            
            logger.error("main "+ e);
        }	    
	}
}

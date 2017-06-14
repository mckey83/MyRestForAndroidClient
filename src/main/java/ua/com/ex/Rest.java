package ua.com.ex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "ua.com.ex" })
public class Rest {
    
    //private static final Logger logger = LoggerFactory.getLogger(Rest.class);
 
	public static void main(String[] args) { 	   
	    SpringApplication.run(Rest.class, args);     
	    //ApplicationContext app = SpringApplication.run(Rest.class, args);	    
	     
//	     RemoteDataService myBean = app.getBean(RemoteDataServiceImpl.class);
//	     try {
//            myBean.updateData();
//        } catch (ServiceException e) {            
//            logger.error("main "+ e);
//        }	    
	}
}

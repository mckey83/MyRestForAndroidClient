package ua.com.ex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "ua.com.ex" })
public class Rest {

	public static void main(String[] args) { 	    
	    SpringApplication.run(Rest.class, args);	           
	}

}

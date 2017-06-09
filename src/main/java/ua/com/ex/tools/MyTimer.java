package ua.com.ex.tools;

import java.time.Duration;
import java.time.Instant;

public class MyTimer {

	private Instant start; 
	private Instant end;

	public void start(){
		start = Instant.now();;
	}

	public void stop(){
		end = Instant.now();
	}

	public Duration getResult(){
		return Duration.between(start, end);
	}

	
}

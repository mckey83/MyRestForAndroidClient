package ua.com.ex.tools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.com.ex.Rest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RemoteFileInformationTest {
	
	@Autowired
	private RemoteFileInformation remoteFileInformation;
	
	@Test
	public void test() {		
		try {
			remoteFileInformation.updateByCategoryId(3);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());			
		}	
		
	}

}

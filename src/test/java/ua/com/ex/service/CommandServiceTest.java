package ua.com.ex.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.com.ex.Rest;
import ua.com.ex.service.interfaces.CommandService;
import ua.com.ex.tools.MyTimer;
import ua.com.ex.tools.file.FileOperation;
import ua.com.ex.tools.path.GetPath;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommandServiceTest {

    @Autowired
    private CommandService commandService;
    
    @Autowired
    private FileOperation fileOperation;

    @Test
    public void test() { 
        int id = 23;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
            Long expect = new Long(10);
            System.out.println("expect "+sdf.format(expect));
            String path = GetPath.getLocalProductImagePath(id);
            fileOperation.setLastModifiedDate(path, expect);
            MyTimer myTimer = new MyTimer ();
            myTimer.start();
            commandService.updateImage();
            myTimer.stop();
            System.out.println("***"+myTimer.getResult()+"***");
            Long actual = fileOperation.getLastModifiedDate(path);
            System.out.println("actual "+sdf.format(actual));
            assertTrue(expect < actual);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Not yet implemented");
        }
        
    }

}

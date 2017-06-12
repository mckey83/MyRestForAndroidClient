package ua.ex.com.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.com.ex.Rest;
import ua.com.ex.reprository.interfaces.ImageRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImagesRepositoryTest {

    private static final int DEFAULT_IMAGE_LENGHT = 1004;
    @Autowired    
    private ImageRepository imageRepository;
    
    @Test
    public void checkLocalProductImageTest() {        
        int id = 24136;
        try {            
            int actual = imageRepository.getProductImageById(id).length();
            assertTrue(actual > 0);
        } catch (Exception e) { 
            e.printStackTrace();
            fail("checkLocalProductImageTest");
        }       
    }
    
    @Test
    public void checkRemoteProductImageTest() {
        int id = 25353;        
        try {
            int actual = imageRepository.getProductImageById(id).length();
            assertTrue(actual > DEFAULT_IMAGE_LENGHT);
        } catch (Exception e) { 
            e.printStackTrace();
            fail("checkRemoteProductImageTest");
        }        
    }
    
    @Test
    public void checkGetDefaultProductImageTest() {        
        try {
            assertEquals(imageRepository.getProductImageById(0).length(), DEFAULT_IMAGE_LENGHT);
        } catch (Exception e) { 
            e.printStackTrace();
            fail("checkGetDefaultTest");
        }        
    }
    
    @Test
    public void checkRemoteCategoryImageTest() {
        int id = 3;        
        try {
            int actual = imageRepository.getCategoryImageById(id).length();
            assertTrue(actual > DEFAULT_IMAGE_LENGHT);
        } catch (Exception e) { 
            e.printStackTrace();
            fail("checkRemoteCategoryImageTest");
        }        
    }
    
    @Test
    public void checkGetDefaultCategoryImageTest() {        
        try {
            assertEquals(imageRepository.getCategoryImageById(0).length(), DEFAULT_IMAGE_LENGHT);
        } catch (Exception e) { 
            e.printStackTrace();
            fail("checkGetDefaultCategoryImageTest");
        }        
    }

}

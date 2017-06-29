package ua.ex.com.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.com.ex.Rest;
import ua.com.ex.configuration.Path;
import ua.com.ex.reprository.ImageRepository;
import ua.com.ex.tools.file.FileOperation;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImagesRepositoryTest {

    private static final int DEFAULT_IMAGE_LENGHT = 1004;

    @Autowired
    @Qualifier("imageCategoryRepository")
    private ImageRepository imageCategoryRepository;
    
    @Autowired
    @Qualifier("imageProductRepository")
    private ImageRepository imageProductRepository;

    @Autowired    
    private FileOperation fileOperation;

    @Test
    public void checkLocalProductImageTest() {        
        int id = 24136;
        try {            
            int actual = imageProductRepository.getById(id).length();
            assertTrue(actual > 0);
        } catch (Exception e) { 
            e.printStackTrace();
            fail("checkLocalProductImageTest");
        }       
    } 
    
    @Test
    public void checkRemoteProductImageTest() {        
        int id = 17933;
        try {            
            imageProductRepository.update(id);
            int actual = imageProductRepository.getById(id).length();
            assertTrue(actual > 0);
        } catch (Exception e) { 
            e.printStackTrace();
            fail("checkLocalProductImageTest");
        }       
    } 

    @Test
    public void checkGetDefaultProductImageTest() {        
        try {
            String image = imageProductRepository.getDefault();
            int expected = (image.getBytes()).length;
            assertEquals(expected, DEFAULT_IMAGE_LENGHT);
        } catch (Exception e) { 
            e.printStackTrace();
            fail("checkGetDefaultTest");
        }        
    }

    @Test
    public void checkLocalCategoryImageTest() {        
        int id = 3;
        try {            
            int actual = imageCategoryRepository.getById(id).length();
            assertTrue(actual > DEFAULT_IMAGE_LENGHT);
        } catch (Exception e) { 
            e.printStackTrace();
            fail("checkLocalProductImageTest");
        }       
    }    

    @Test
    public void checkGetDefaultCategoryImageTest() {        
        try {
            assertEquals(0, imageCategoryRepository.getById(0).length());
        } catch (Exception e) { 
            e.printStackTrace();
            fail("checkGetDefaultCategoryImageTest");
        }        
    }

    @Test
    public void saveProductImageTest() {
        int id = 24136;
        try {
            String expect = imageProductRepository.getById(id);         
            String path = Path.getLocalProductImagePath(id);           
            fileOperation.cleanOldFile(path);
            assertFalse(fileOperation.isExist(path));
            imageProductRepository.save(id, expect);            
            String actual = imageProductRepository.getById(id);
            assertEquals(expect.length(), actual.length());
            assertTrue(fileOperation.isExist(path));
        } catch (Exception e) {            
            fail("saveProductImageTest" + e.getMessage());           
        }         
    } 
    
    @Test
    public void saveProductImage2Test() {
        int id = 0;
        try {
            String expect = Base64.encodeBase64String("test&test&test".getBytes());
            imageProductRepository.save(id, expect);            
            String actual = imageProductRepository.getById(id);
            assertEquals(expect, actual);             
            fileOperation.cleanOldFile(Path.getLocalProductImagePath(id));
        } catch (Exception e) {            
            fail("saveProductImage2Test" + e.getMessage());           
        }         
    } 

}

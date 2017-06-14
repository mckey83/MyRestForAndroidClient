package ua.com.ex.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.com.ex.Rest;
import ua.com.ex.model.Category;
import ua.com.ex.service.impl.CategoryServiceImpl;
import ua.com.ex.tools.imageloader.ImageLocalLoader;
import ua.com.ex.tools.path.GetPath;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryServiceImplTest {   

    @Autowired
    private CategoryServiceImpl service;  
    
    @Autowired
    @Qualifier("imageLocalLoader")
    private ImageLocalLoader imageLocalLoader;

    @Test
    public void category3Imagetest() {
        int categoryId = 3;       
        try {
            String expected = getItemFromResource(categoryId);            
            Category category = service.getCategoryById(categoryId);
            String actual = category.getImageBase64();           
            assertEquals(expected, actual);
        } catch (Exception e) {
            System.out.println("imageCategory3test() : "+ e.toString());
        }
    }

    @Test
    public void categoryByParentIdNotFoundtest() {
        int categoryId = 92;     
        try {            
            assertEquals(0, service.getCategoryByParentId(categoryId).size());           
        } catch (Exception e) {
            fail("getCategoryById 92" + e.getMessage());
        }        
    }
    
    @Test
    public void categoryByParentIdFoundtest() {
        int categoryId = 1;     
        try {            
            assertEquals(12, service.getCategoryByParentId(categoryId).size());           
        } catch (Exception e) {
            fail("getCategoryById 92" + e.getMessage());
        }        
    }
    
    @Test
    public void category333test() {
        int categoryId = 333;     
        try {            
            service.getCategoryById(categoryId);            
        } catch (Exception e) {
            assertEquals("getCategoryById not found 333", e.getMessage());
        }
    }

    @Test
    public void categoryByParenIdtest() {
        int categoryId = 1;     
        List<Category> actual;
        try {
            actual = service.getCategoryByParentId(categoryId);
            assertEquals(12, actual.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }         
    }   

    private String getItemFromResource(int categoryId) throws Exception {   
        String path = GetPath.getLocalCategoryImagePath(categoryId);
        String result = imageLocalLoader.getImage(path);
        if( result.isEmpty() ) {            
            result = imageLocalLoader.getDafaultImage();      
        }
        return result;
    }   

}

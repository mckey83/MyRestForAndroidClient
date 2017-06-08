package ua.com.ex.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.com.ex.Rest;
import ua.com.ex.exception.ToolsException;
import ua.com.ex.model.Category;
import ua.com.ex.service.impl.CategoryServiceImpl;
import ua.com.ex.tools.FileOperation;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryServiceImplTest {

    private static final String DEFAULT_IMAGE = "/images/ex.png";

    @Autowired
    private CategoryServiceImpl service;

    @Autowired
    private FileOperation fileOperation;    

    @Test
    public void category3Imagetest() {
        int categoryId = 3;
        String path = "/images/categories/cat_"+ categoryId + ".png";
        try {
            String expected = getItemFromResource(path);
            Category category = service.getCategoryById(categoryId);
            String actual = category.getImageBase64();           
            assertEquals(expected, actual);
        } catch (Exception e) {
            System.out.println("imageCategory3test() : "+ e.toString());
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

    private String getItemFromResource(String path) throws ToolsException {       
        ByteArrayOutputStream stream = fileOperation.readFile(path);
        String result = Base64.encodeBase64String(stream.toByteArray());
        if( result.isEmpty() ) {
            stream = fileOperation.readFile(DEFAULT_IMAGE);
            result = Base64.encodeBase64String(stream.toByteArray());            
        }
        return result;
    }    

}

package integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ua.com.ex.Rest;
import ua.com.ex.model.Category;
import ua.com.ex.service.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerIT {

    private final String path = "/category/";
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testCategoryById() {
        int categoryId = 3;
        String url = path + categoryId;
        Category actual = (restTemplate.getForEntity(url, Category.class)).getBody();        
        Category expected = categoryService.getCategoryById(categoryId);
        assertEquals(expected,actual);       
    }

    @Test
    public void testCategoryByNotTheSameId() {
        int firstCategoryId = 3;
        int secondCategoryId = 4;
        String url = path + firstCategoryId;
        Category actual = (restTemplate.getForEntity(url, Category.class)).getBody();        
        Category expected = categoryService.getCategoryById(secondCategoryId);
        assertNotEquals(expected, actual);       
    }
    
    @Test
    public void testCategoryByParentId() {
        int firstCategoryId = 1;
        int secondCategoryId = 1;
        String url = path + firstCategoryId+"/parentid";
        ResponseEntity<Category[]> responseEntity = restTemplate.getForEntity(url,  Category[].class);
        List<Category> actual = new ArrayList<Category>(Arrays.asList(responseEntity.getBody()));        
        List<Category> expected = categoryService.getCategoryByParentId(secondCategoryId);
        assertEquals(expected, actual);       
    }

}

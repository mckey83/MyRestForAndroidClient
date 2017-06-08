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
import ua.com.ex.model.Product;
import ua.com.ex.service.interfaces.ProductService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIT {
    
    private final String path = "/product/";
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private ProductService productService;
        
    @Test
    public void testProductById() throws Exception {
        int productId = 92;
        String url = path + productId;
        Product actual = (restTemplate.getForEntity(url, Product.class)).getBody();        
        Product expected = productService.getProductById(productId);
        assertEquals(expected, actual);        
    }
    
    @Test
    public void testProductByNotTheSameId() throws Exception {
        int firstProductId = 92;
        int secondProductId = 104;
        String url = path + firstProductId;        
        Product firstProduct = (restTemplate.getForEntity(url, Product.class)).getBody();
        Product secondProduct = productService.getProductById(secondProductId);
        assertNotEquals(firstProduct, secondProduct);        
    }
    
    @Test
    public void testProductByCategoryId() throws Exception {
        int categoryId = 92;
        int page = 1;
        int itemQuantity = 5;
        String url = path + categoryId + "/category/" + page + "/page/" + itemQuantity;
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity(url,  Product[].class);
        List <Product> actual= new ArrayList<Product>(Arrays.asList(responseEntity.getBody()));
        List <Product> expected = productService.getProductByCategoryIdPaging(categoryId, page, itemQuantity);
        assertEquals(expected, actual);        
    }

}

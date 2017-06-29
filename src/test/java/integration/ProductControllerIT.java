package integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import ua.com.ex.model.view.ProductCard;
import ua.com.ex.model.view.ProductCatalogItem;
import ua.com.ex.service.ProductService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIT {

	private final String path = "/product/";

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ProductService productService; 

	@Test
	public void getProductCatalogItemByCategoryIdPagingTest()  {
		int categoryId = 92;
		int page = 1;
		int itemQuantity = 5;
		String url = path + categoryId + "/category/" + page + "/page/" + itemQuantity;
		List<Product> expectedProduct = new ArrayList<>();
		try {
			expectedProduct = productService.getProductByCategoryIdPaging(categoryId, page, itemQuantity);
		} catch (Exception e) {
			fail("getProductCatalogItemByCategoryIdPagingTest() "+ e.getMessage() );           
		}
		List <ProductCatalogItem> expectedProductCatalogItem = new ArrayList<>();
		for(Product current : expectedProduct){
			expectedProductCatalogItem.add(new ProductCatalogItem(current) );
		}
		ResponseEntity<ProductCatalogItem[]> responseEntity = restTemplate.getForEntity(url,  ProductCatalogItem[].class);
		List <ProductCatalogItem> actual = new ArrayList<ProductCatalogItem>(Arrays.asList(responseEntity.getBody()));
		assertEquals(expectedProductCatalogItem, actual);        
	}

	@Test
	public void getProductCatalogItemByCategoryIdPagingNotZeroTest()  {
		int categoryId = 92;
		int page = 1;
		int itemQuantity = 5;
		String url = path + categoryId + "/category/" + page + "/page/" + itemQuantity;
		ResponseEntity<ProductCatalogItem[]> responseEntity = restTemplate.getForEntity(url,  ProductCatalogItem[].class);
		List <ProductCatalogItem> actual = new ArrayList<ProductCatalogItem>(Arrays.asList(responseEntity.getBody()));
		assertTrue(actual.size() > 0);        
	}

	@Test
	public void getProductCatalogItemByCategoryIdPagingNoZeroTest()  {
		int categoryId = -92;
		int page = 1;
		int itemQuantity = 5;
		String url = path + categoryId + "/category/" + page + "/page/" + itemQuantity;
		ResponseEntity<ProductCatalogItem[]> responseEntity = restTemplate.getForEntity(url,  ProductCatalogItem[].class);
		List <ProductCatalogItem> actual = new ArrayList<ProductCatalogItem>(Arrays.asList(responseEntity.getBody()));
		assertTrue(actual.size() == 0);        
	}

	@Test
	public void getProductCatalogItemByCategoryIdPagingZeroTest()  {
		int categoryId = -92;
		int page = 1;
		int itemQuantity = 5;
		String url = path + categoryId + "/category/" + page + "/page/" + itemQuantity;
		ResponseEntity<ProductCatalogItem[]> responseEntity = restTemplate.getForEntity(url,  ProductCatalogItem[].class);
		List <ProductCatalogItem> actual = new ArrayList<ProductCatalogItem>(Arrays.asList(responseEntity.getBody()));
		assertTrue(actual.size() == 0);        
	}

	@Test
	public void getProductCardWithChildrenTest()  {
		int productCardId = 38751;        
		String url = path +productCardId;
		ResponseEntity<ProductCard> responseEntity = restTemplate.getForEntity(url,  ProductCard.class);
		ProductCard actual = responseEntity.getBody();  
		System.out.println(actual);
		assertEquals(19,  actual.getChildren().size());             
	}

	@Test
	public void getProductCardWithOurChildrenTest()  {
		int productCardId = 36979;        
		String url = path +productCardId;
		ResponseEntity<ProductCard> responseEntity = restTemplate.getForEntity(url,  ProductCard.class);
		ProductCard actual = responseEntity.getBody(); 
		System.out.println(actual);
		assertEquals(0,  actual.getChildren().size());             
	}
}

package ua.ex.com.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.com.ex.Rest;
import ua.com.ex.model.Product;
import ua.com.ex.reprository.interfaces.ProductRepository;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void findProductParentOnlyByCategoryIdTest() {		
		int categoryId = 3;
		List<Product> productAll = productRepository.findProductParentOnlyByCategoryId(categoryId);
		for(Product current :productAll){			
			if(current.getId() != current.getGroupId()){
				fail("findProductParentOnlyByCategoryIdTest");
			}
		}		
	}

	@Test
	public void findProductParentOnlyByCategoryIdPaginationTest() {		
		int categoryId = 3;
		int pageNumber = 1;
		int itemQuantity = 13;
		PageRequest page = gotoPage(pageNumber, itemQuantity);
		List<Product> productAll = productRepository.findProductParentOnlyByCategoryIdPagination(page, categoryId);
		for(Product current :productAll){			
			if(current.getId() != current.getGroupId()){
				fail("findProductParentOnlyByCategoryIdTest");
			}
		}		
		assertEquals(itemQuantity, productAll.size());
	}	

	private PageRequest gotoPage(int page, int itemQuantity) {      
		return new PageRequest(page, itemQuantity);
	}

	@Test
	public void findProductByGroupIdTest() {		
		int groupId = 1697;		
		List<Product> productAll = productRepository.findProductByGroupId(groupId);
		for(Product current :productAll){	
			System.out.println(current);
			if(groupId != current.getGroupId()){				
				fail("findProductParentOnlyByCategoryIdTest");
			}
		}		
	}
}

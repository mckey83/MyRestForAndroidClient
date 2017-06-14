package ua.com.ex.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.ex.model.Product;
import ua.com.ex.model.view.ProductCard;
import ua.com.ex.model.view.ProductCatalogItem;
import ua.com.ex.service.interfaces.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
	@Autowired
	ProductService productService;	

	@GetMapping("/{id}/category/{page}/page/{itemquantity}")
	public List<ProductCatalogItem> getProductCatalogItemByCategoryId(@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("itemquantity") int itemQuantity) {
		
	    List<Product> productAll = new ArrayList<>();
	    ArrayList<ProductCatalogItem> result = new ArrayList<ProductCatalogItem>();
	    try {
	        productAll = productService.getProductByCategoryIdPaging(id ,page, itemQuantity);
        } catch (Exception e) {
            logger.info("getProductByCategoryId() "+ e.getMessage());
            return result;
        } 
	    for(Product current : productAll){
	        result.add(new ProductCatalogItem(current));
	    }
	    return result;
	    
	}	

	@GetMapping("/{id}")
    public ProductCard getProductCardById(@PathVariable("id") int id) {	
	    logger.info("getProductCardById = "+ id);
		Product parent = new Product();
		List<Product> children = new ArrayList<>();
		List<Product> productAllByGroupId = new ArrayList<>();      
        try {
            productAllByGroupId = productService.findProductByGroupId(id);
        } catch (Exception e) {
            logger.error("productAllByGroupId() "+ e.getMessage());
            return new ProductCard();
        } 
        for(Product current : productAllByGroupId){
        	if(current.getId() == id){
        		parent = current;
        	} else {
        		children.add(current);
        	}
        }       
        return new ProductCard(parent, children);  
    }
}

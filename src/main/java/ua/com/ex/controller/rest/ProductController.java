package ua.com.ex.controller.rest;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.ex.model.Product;
import ua.com.ex.model.view.ProductCard;
import ua.com.ex.model.view.ProductCatalogItem;
import ua.com.ex.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;	

    @GetMapping("/{id}/category/{page}/page/{itemquantity}")
    public ResponseEntity<?> getProductCatalogItemByCategoryId(@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("itemquantity") int itemQuantity) {
        List<Product> productAll = new ArrayList<>();
        ArrayList<ProductCatalogItem> result = new ArrayList<ProductCatalogItem>();
        try {
            productAll = productService.getProductByCategoryIdPaging(id ,page, itemQuantity);
        } catch (Exception e) {
            String message = e.getMessage();
            logger.warn(message);
            return new ResponseEntity<>(message, NOT_FOUND);
        } 
        for(Product current : productAll){
            result.add(new ProductCatalogItem(current));
        }
        return new ResponseEntity<>(result, OK);


    }	

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductCardById(@PathVariable("id") int id) {		
        ProductCard result = null;
        Product parent = new Product();
        List<Product> children = new ArrayList<>();
        List<Product> productAllByGroupId = new ArrayList<>();      
        try {
            productAllByGroupId = productService.findProductByGroupId(id);
        } catch (Exception e) {
            String message = e.getMessage();
            logger.warn(message);
            return new ResponseEntity<>(message, NOT_FOUND);
        } 
        if (!productAllByGroupId.isEmpty()){
            for(Product current : productAllByGroupId){
                if(current.getId() == id){
                    parent = current;
                } else {
                    children.add(current);
                }
            }   
            result = new ProductCard(parent, children);
        } else {
            result = new ProductCard(parent, new ArrayList<>());
        }		
        return new ResponseEntity<>(result, OK);
    }
}

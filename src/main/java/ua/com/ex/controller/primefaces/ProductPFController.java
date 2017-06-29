package ua.com.ex.controller.primefaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ua.com.ex.model.Product;
import ua.com.ex.service.ProductService;

@Getter
@Setter
@Controller
@ViewScoped
@ManagedBean
public class ProductPFController implements Serializable{    

    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = LoggerFactory.getLogger(ProductPFController.class);

    @Autowired
    private ProductService productService;  

    private List<Product> list = new ArrayList<>(); 
    
    private Product selected;    

    @PostConstruct
    public void init() {            
        update(1);
    }

    public void update(int  parentId){        
        try {
            list = productService.getProductByCategoryId(parentId);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    } 

}

package ua.com.ex.controller.primefaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ua.com.ex.model.Category;
import ua.com.ex.model.Product;
import ua.com.ex.service.CategoryService;
import ua.com.ex.service.ProductService;

@Getter
@Setter
@Controller
@ViewScoped
@ManagedBean
public class ProductPFController implements Serializable{    

    private static final long serialVersionUID = 1L;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ContentPFController contentPFController;

    private List<Product> list = new ArrayList<>(); 

    private Product selected; 

    private int parentId; 


    @PostConstruct
    public void init() { 
        
        parentId = contentPFController.getParentId();
        update();
    }

    public void update(){
        list = productService.getProductByCategoryId(parentId);
    } 

    public void back(){        
        Category parentCategory = categoryService.getCategoryById(parentId);
        int parentCategoryParentId = parentCategory.getParentId();
        contentPFController.setParentId(parentCategoryParentId);
        contentPFController.showCategory();        
    }
}

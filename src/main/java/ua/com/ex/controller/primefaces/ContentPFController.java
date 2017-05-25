package ua.com.ex.controller.primefaces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Controller;


@Controller
@ManagedBean
@SessionScoped
public class ContentPFController {
    
    @PostConstruct
    public void init() {
        parentId = 1;
    }
    
    private String content = "/content/category.xhtml";
    
    private int parentId ;
    
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        System.out.println("getContent() "+ content + " parentId = "+ parentId);
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    

    public void showCategory(){      
        content = "/content/category.xhtml";
    }
    
    public void showProduct(){
        content = "/content/product.xhtml";
    }
    
    
}

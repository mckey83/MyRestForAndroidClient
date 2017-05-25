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
import ua.com.ex.service.CategoryService;

@Getter
@Setter
@Controller
@ViewScoped
@ManagedBean
public class CategoryPFController implements Serializable{

    private static final long serialVersionUID = 1L;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ContentPFController contentPFController;

    private List<Category> list = new ArrayList<>(); 

    private Category selected;

    private int parentId ;

    @PostConstruct
    public void init() {
        System.out.println("CategoryPFController.init()");
        selected = new Category();
        int id = contentPFController.getParentId();
        selected.setId(id);
        System.out.println("CategoryPFController.init() id = "+ id);
        update();
    }

    public void update(){
        parentId = selected.getId();
        list = categoryService.getCategoryByParentId(parentId);
        if(list.isEmpty() && selected.getId() > 1){
            contentPFController.setParentId(parentId);
            contentPFController.showProduct();
        }
    } 

}

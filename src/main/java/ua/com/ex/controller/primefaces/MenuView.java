package ua.com.ex.controller.primefaces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ua.com.ex.service.CategoryService;

@Getter
@Setter
@Controller
@ViewScoped
@ManagedBean
public class MenuView {

    private MenuModel model2 = new DefaultMenuModel();
    private MenuModel model5 = new DefaultMenuModel();
    
    @Autowired
    private CategoryService categoryService;

    @PostConstruct
    public void init() {
        

    }
    
   

}

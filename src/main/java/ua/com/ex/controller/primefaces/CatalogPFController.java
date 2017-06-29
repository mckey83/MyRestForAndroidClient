package ua.com.ex.controller.primefaces;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CatalogPFController implements Serializable{

    private static final long serialVersionUID = 1L;   

    private static final Logger logger = LoggerFactory.getLogger(CatalogPFController.class);

    private MenuModel model;

    private boolean rend = true;   

    @Autowired
    private CategoryService categoryService; 

    @Autowired
    private ProductPFController productPFController;

    @PostConstruct
    public void init() {
        model =  new DefaultMenuModel();
        List<Category> upCategoryAll;
        try {
            upCategoryAll = categoryService.getCategoryByParentId(1);
            if (upCategoryAll.isEmpty()) {
                return;
            }
            for(Category upCurrent : upCategoryAll ){           
                DefaultSubMenu submenu = createSubMenu(upCurrent);
                System.out.println(upCurrent);
                List<Category> lowCategoryAll = categoryService.getCategoryByParentId(upCurrent.getId());           
                if(lowCategoryAll.isEmpty()){                
                    DefaultMenuItem item = createOneLowCategoryByOneUp(upCurrent);                            
                    submenu.addElement(item);
                } else {               
                    createLowCategoryAll(submenu, lowCategoryAll);
                }
                model.addElement(submenu);            
            }
        } catch (Exception e) {
            logger.error(e.getMessage());            
        }
    }

    private DefaultSubMenu createSubMenu(Category upCurrent) {
        DefaultSubMenu submenu = new DefaultSubMenu(upCurrent.getName());
        submenu.setExpanded(false);
        return submenu;
    }

    private void createLowCategoryAll(DefaultSubMenu submenu, List<Category> lowCategoryAll) {
        for(Category lowCurrent : lowCategoryAll ){                    
            if (lowCurrent.getProductQuantity() > 0){
                DefaultMenuItem item = createOneLowCategoryByOneUp(lowCurrent);                                               
                submenu.addElement(item);   
            }
        }
    }

    private DefaultMenuItem createOneLowCategoryByOneUp(Category upCurrent) {
        DefaultMenuItem item = new DefaultMenuItem(upCurrent.getName());
        item.setCommand("#{catalogPFController.update('"+upCurrent.getId()+"')}");
        return item;
    }    

    public void update(int id){
        productPFController.update(id); 
        if(rend == false) {           
            rend = true;
            updateView();
        }
        else{
            rend = false;
            updateView();
            rend = true;
            updateView();            
        }
    }

    public void updateView(){
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.update("optionPanel");
    }
}

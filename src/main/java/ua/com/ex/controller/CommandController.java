package ua.com.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.ex.model.Category;
import ua.com.ex.model.Product;
import ua.com.ex.reprository.CategoryFileRepository;
import ua.com.ex.reprository.CategoryRepository;
import ua.com.ex.reprository.ProductFileRepository;
import ua.com.ex.reprository.ProductRepository;
import ua.com.ex.service.RemoteDataService;

@RestController
@RequestMapping("/command")
public class CommandController {

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private RemoteDataService remoteDataService;

    @Autowired
    private CategoryFileRepository categoryFileRepository;

    @Autowired
    private ProductFileRepository productFileRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/shutdown/start")
    public void shutdown() {
        ((ConfigurableApplicationContext) appContext).close();    
        System.exit(0);
    }

    @GetMapping("/update")
    public String update() {
        return remoteDataService.updateFromEx();
    }

    @GetMapping("/restart")
    public void restart() {
        ((ConfigurableApplicationContext) appContext).refresh();
    }

    @GetMapping("/import")
    public String importData() {         
        List<Category> categories = categoryFileRepository.getAll();
        List<Product> products = productFileRepository.getAll();

        if (!categories.isEmpty() && !products.isEmpty()){
            String result = "";
            for(Product current: products){
              //  productRepository.save(current);
                result+=current.getId()+" "+current.getName()+" "+current.getPrice()+" "+current.getQuantity()+" "+current.getCategoryId()+"<br/>";                        
            }  
            for(Category current: categories){
            //    int productQuantity = categoryRepository.findProductQuantityByCategoryId(current.getId());
               // current.setProductQuantity(productQuantity);
               // categoryRepository.save(current);
            } 

            return "OK imported categories: "+categories.size() +" products : "+ products.size()+result;
        }
        else{
            return "ERROR";  
        }
    }
}




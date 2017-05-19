package ua.com.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.ex.model.Category;
import ua.com.ex.reprository.CategoryFileRepository;
import ua.com.ex.reprository.CategoryRepository;
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
    private CategoryRepository categoryRepository;

    @GetMapping("/shutdown/start")
    public void shutdown() {
        ((ConfigurableApplicationContext) appContext).close();    
        System.exit(0);
    }

    @GetMapping("/update")
    public void update() {
        remoteDataService.updateFromEx();
    }

    @GetMapping("/restart")
    public void restart() {
        ((ConfigurableApplicationContext) appContext).refresh();
    }

    @GetMapping("/import")
    public String importData() {        
        List<Category> categories = categoryFileRepository.getAll();
        if (!categories.isEmpty()){
            for(Category current: categories){
                categoryRepository.save(current);
            }           
            return "OK imported : "+categories.size();
        }
        else{
            return "Import file not found.";  
        }
    }

}




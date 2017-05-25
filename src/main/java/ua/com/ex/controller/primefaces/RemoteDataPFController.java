package ua.com.ex.controller.primefaces;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ua.com.ex.reprository.CategoryRepository;
import ua.com.ex.reprository.ProductRepository;
import ua.com.ex.service.RemoteDataService;

@Getter
@Setter
@Controller
@ViewScoped
@ManagedBean
public class RemoteDataPFController implements Serializable{

    @Autowired
    private RemoteDataService remoteDataService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final long serialVersionUID = 1L;

    private String categories = " categories : ";
    private String products = " products : ";
    private String time = " time ";

    @PostConstruct
    public void init() {
        categories = " categories : "+categoryRepository.findAll().size();
        products = " products : "+productRepository.findAll().size();

    }

    public void update() {  
        long start = System.currentTimeMillis();
        remoteDataService.updateFromEx();        
        categories = " categories : "+categoryRepository.findAll().size();
        products = " products : "+productRepository.findAll().size();
        long elapsedTimeMillis = System.currentTimeMillis()-start;        
        time = " time "+elapsedTimeMillis/1000F+ " sek ";
    }

}

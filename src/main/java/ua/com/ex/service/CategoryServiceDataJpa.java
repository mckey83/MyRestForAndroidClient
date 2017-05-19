package ua.com.ex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.ex.model.Category;
import ua.com.ex.reprository.CategoryRepository;
import ua.com.ex.reprository.ImageRepository;

@Service("categoryService")
public class CategoryServiceDataJpa implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
    ImageRepository imageRepository;

	@Override
	public Category getCategoryById(int id) {	   	    
		return prepareForSend(categoryRepository.findOne(id)); 
	}

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}
	
	public List<Category> getCategoryByParentId(int categoryByParentId){	    
	    List<Category>  source = categoryRepository.getCategoryByParentId(categoryByParentId);		   
	    List<Category> result = new ArrayList<>();
	    for (Category current : source) {                        
                result.add(prepareForSend(current));          
        }    
        return result;    
	}
	
	
    private Category prepareForSend(Category current) {
        String image = imageRepository.getCategoryImageById(current.getId());
        if (!image.isEmpty()) {
            current.setImageBase64(image);
        }
        else {
            current.setImageBase64("null");
        }       
        return current;
    }
	
	
}

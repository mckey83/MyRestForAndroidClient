package ua.com.ex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.ex.model.Category;
import ua.com.ex.reprository.CategoryReprository;
import ua.com.ex.reprository.ImageReprository;

@Service("categoryService")
public class CategoryServiceDataJpa implements CategoryService {

	@Autowired
	CategoryReprository categoryReprository;
	
	@Autowired
    ImageReprository imageReprository;

	@Override
	public Category getCategoryById(int id) {	   	    
		return prepareForSend(categoryReprository.findOne(id)); 
	}

	@Override
	public List<Category> getAll() {
		return categoryReprository.findAll();
	}
	
	public List<Category> getCategoryByParentId(int categoryByParentId){	    
	    List<Category>  source = categoryReprository.getCategoryByParentId(categoryByParentId);		   
	    List<Category> result = new ArrayList<>();
	    for (Category current : source) {	       
            if(isNeed(current)){                
                result.add(prepareForSend(current));
            }  
        }    
        return result;	    
	}

	private boolean isNeed(Category current){
        return current.getExtra()==0 && current.getEnabled()==1;	    
    }
	
    private Category prepareForSend(Category current) {
        String image = imageReprository.getCategoryImageById(current.getId());
        if (!image.isEmpty()) {
            current.setImageBase64(image);
        }
        else {
            current.setImageBase64("null");
        }
        current.setQuantityOfProducts(categoryReprository.findProductQuantityByCategoryId(current.getId()));
        return current;
    }
	
	
}

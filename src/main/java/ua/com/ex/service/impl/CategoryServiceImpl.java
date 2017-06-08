package ua.com.ex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.com.ex.exception.ServiceException;
import ua.com.ex.model.Category;
import ua.com.ex.reprository.interfaces.CategoryRepository;
import ua.com.ex.reprository.interfaces.ImageRepository;
import ua.com.ex.service.interfaces.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    @Qualifier("imageRemoteRepository")
    private ImageRepository imageRepository;

    @Override
    public Category getCategoryById(int id) throws Exception {	
        Category category = categoryRepository.findOne(id);
        if (category == null){
            throw new ServiceException("getCategoryById not found "+id);
        }
        return prepareForSend(category); 
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getCategoryByParentId(int categoryByParentId) throws Exception{	    
        List<Category>  source = categoryRepository.getCategoryByParentId(categoryByParentId);
        if (source.isEmpty()){
            throw new ServiceException("getCategoryByParentId not found "+categoryByParentId);
        }        
        for (Category current : source) {                        
            prepareForSend(current);          
        }    
        return source;    
    }

    private Category prepareForSend(Category current) throws Exception {              
        current.setImageBase64(imageRepository.getCategoryImageById(current.getId()));    
        return current;
    }

    @Override
    public void save(Category category) {        
        categoryRepository.save(category);
    }

    @Override
    public int findProductQuantityByCategoryId(int categoryId) {
        return categoryRepository.findProductQuantityByCategoryId(categoryId);
    }
}

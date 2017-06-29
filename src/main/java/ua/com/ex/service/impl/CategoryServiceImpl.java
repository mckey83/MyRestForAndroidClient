package ua.com.ex.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.com.ex.exception.ServiceException;
import ua.com.ex.model.Category;
import ua.com.ex.reprository.CategoryRepository;
import ua.com.ex.reprository.ImageRepository;
import ua.com.ex.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    @Qualifier("imageCategoryRepository")
    private ImageRepository imageRepository;

    @Override
    public Category getCategoryById(int id) throws Exception {	
        Category category = categoryRepository.findOne(id);
        if (category == null){
            String message = "getCategoryById not found "+id;
            logger.warn(message);
            throw new ServiceException();
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
            String message = "getCategoryByParentId not found "+categoryByParentId;
            logger.warn(message);
            throw new ServiceException();
        }        
        for (Category current : source) {                        
            prepareForSend(current);          
        }    
        return source;    
    }

    private Category prepareForSend(Category current) throws Exception {              
        current.setImageBase64(imageRepository.getById(current.getId()));    
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

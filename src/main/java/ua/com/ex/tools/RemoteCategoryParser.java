package ua.com.ex.tools;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.ex.exception.ServiceException;
import ua.com.ex.model.Category;
import ua.com.ex.model.mapper.CategoryMapper;
import ua.com.ex.service.interfaces.CategoryService;


@Component(value="remoteCategoryParser")
public class RemoteCategoryParser {

    protected static SqlFileParser parser = new SqlFileParser();

    private static final Logger logger = LoggerFactory.getLogger(RemoteCategoryParser.class);

    @Autowired
    private CategoryMapper categoryMapper;    

    @Autowired
    private CategoryService categoryService;    

    private static final int EXTRA = 17;

    private static final int COLUMN_CATEGORY_ENABLED = 10;

    public void updateData() throws Exception { 
        System.out.println("RemoteCategoryParser.updateData()");
        List<Category> categories = getCategories();       
        if (!categories.isEmpty() ){            
            for(Category current: categories){
                int productQuantity = categoryService.findProductQuantityByCategoryId(current.getId());               
                current.setProductQuantity(productQuantity);
                categoryService.save(current);
            }            
        }       
    }   
   
    private List<Category> getCategories() throws ServiceException{
        ArrayList<ArrayList<String>> categoriesAsString = new ArrayList<>();
        ArrayList<Category> result = new ArrayList<>();
        try{
            categoriesAsString = parser.get("INSERT INTO `categories` VALUES", "/*!40000 ALTER TABLE `categories` ENABLE KEYS */;", "localexbase.sql", 33);
            for (ArrayList<String> current: categoriesAsString){
                if (isNeed(current)) {                
                    result.add(categoryMapper.getCategory(current));                
                }             
            }
        } catch(Exception e){
            logger.error("getCategories() ", e);
            throw new ServiceException("getCategories() ", e);
        }
        return result;
    }    

    private boolean isNeed(ArrayList<String> current) {   
        int enabled = 0;
        int extra = 0;
        try{
            enabled = (int) Integer.parseInt(current.get(COLUMN_CATEGORY_ENABLED));
            extra = (int) Integer.parseInt(current.get(EXTRA));             
        } catch (Exception e) {
            e.printStackTrace();   
        }
        return enabled == 1 &&  extra == 0;
    }   

}



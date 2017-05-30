package ua.com.ex.model.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import ua.com.ex.model.Category;

@Component("categoryMapper")
public class CategoryMapperImpl implements CategoryMapper {

    private static final int COLUMN_PARENT_ID = 2;
    private static final int COLUMN_NAME = 1;
    private static final int COLUMN_ID = 0;
    
    @Override
    public Category getCategory(ArrayList<String> fields) {
        Category result = new Category();
        try{
            int id = (int) Integer.parseInt(fields.get(COLUMN_ID));
            result.setId(id);    
            String name = fields.get(COLUMN_NAME);            
            result.setName(name.toLowerCase());
            int parentId = (int) Integer.parseInt(fields.get(COLUMN_PARENT_ID));
            result.setParentId(parentId);
        }catch(Exception e){
            e.printStackTrace();  
        }
        return result;   
    }

}

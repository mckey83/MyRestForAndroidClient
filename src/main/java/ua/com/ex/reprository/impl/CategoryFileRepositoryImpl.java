package ua.com.ex.reprository.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import ua.com.ex.model.Category;
import ua.com.ex.reprository.CategoryFileRepository;
import ua.com.ex.reprository.impl.file.Import;

@Repository("categoryFileRepository")
public class CategoryFileRepositoryImpl implements CategoryFileRepository {

    private static final int EXTRA = 17;
    private static final int COLUMN_ENABLED = 10;
    private static final int COLUMN_PARENT_ID = 2;
    private static final int COLUMN_NAME = 1;
    private static final int COLUMN_ID = 0;

    @Override
    public ArrayList<Category> getAll() {
        Import importCategory = new Import();
        ArrayList<ArrayList<String>> categories = importCategory.get("INSERT INTO `categories` VALUES", "*!40000 ALTER TABLE `categories` ENABLE KEYS */; ", "localexbase.sql", 33);
        ArrayList<Category> result = new ArrayList<>();
        Category root = new Category();
        root.setId(1);
        root.setParentId(0);
        result.add(root);
        for (ArrayList<String> current: categories){
            if (isNeed(current)) {
                result.add(mapperCategory(current));                
            }             
        }
        return result;
    }

    private boolean isNeed(ArrayList<String> current) {   
        int enabled = 0;
        int extra = 0;
        try{
            enabled = (int) Integer.parseInt(current.get(COLUMN_ENABLED));
            extra = (int) Integer.parseInt(current.get(EXTRA));             
        } catch (Exception e) {
            e.printStackTrace();   
        }
        return enabled == 1 &&  extra == 0;
    }

    private Category mapperCategory(ArrayList<String> fields){
        Category result = new Category();
        try{
            int id = (int) Integer.parseInt(fields.get(COLUMN_ID));
            result.setId(id);    
            String name = fields.get(COLUMN_NAME);
            result.setName(name);
            int parentId = (int) Integer.parseInt(fields.get(COLUMN_PARENT_ID));
            result.setParentId(parentId);
        }catch(Exception e){
            e.printStackTrace();  
        }
        return result;        
    }
}

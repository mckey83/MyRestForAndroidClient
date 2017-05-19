package ua.com.ex.reprository.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import ua.com.ex.model.Category;
import ua.com.ex.reprository.CategoryFileRepository;
import ua.com.ex.reprository.impl.file.ImportCategory;

@Repository("categoryFileRepository")
public class CategoryFileRepositoryImpl implements CategoryFileRepository {

    @Override
    public ArrayList<Category> getAll() {
        ImportCategory importCategory = new ImportCategory();

        ArrayList<ArrayList<String>> categories = importCategory.get("INSERT INTO `categories` VALUES", "*!40000 ALTER TABLE `categories` ENABLE KEYS */; ", "localexbase.sql");
        ArrayList<Category> result = new ArrayList<>();
        for (ArrayList<String> current: categories){
            result.add(mapperCategory(current));
        }
        return result;
    }

    private Category mapperCategory(ArrayList<String> fields){
        Category result = new Category();
        try{
            int id = (int) Integer.parseInt(fields.get(0));
            result.setId(id);        
            result.setName(fields.get(1));
            int parentId = (int) Integer.parseInt(fields.get(2));
            result.setParentId(parentId);
        }catch(Exception e){
            for(String current:fields)
                System.out.print("="+current+"=");
        }
        return result;        
    }
}

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
        Category root = new Category();
        root.setId(1);
        root.setParentId(0);
        result.add(root);
        
        for (ArrayList<String> current: categories){
            if (isNeed(current)) {
                result.add(mapperCategory(current));
                System.out.println("+++aded : "+ current.get(0)+ ". "+current.get(1)+ " | "+current.get(2) + " | "+current.get(3)+ " | "+current.get(4));
            } 
            else {
                System.out.println("---not need" +current.get(0)+ ". "+current.get(1)+ " | "+current.get(2) + " | "+current.get(3)+ " | "+current.get(4) );
            }
        }
        return result;
    }

    private boolean isNeed(ArrayList<String> current) {   
        int enable=0;
        int extra =0;

        try{
            enable = (int) Integer.parseInt(current.get(3));
            extra = (int) Integer.parseInt(current.get(4)); 
        } catch (Exception e) {
            System.out.println("?????"+current.get(1));
            enable=0;  extra =0;
           // return false;
        }
        return enable == 1 &&  extra == 0;
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

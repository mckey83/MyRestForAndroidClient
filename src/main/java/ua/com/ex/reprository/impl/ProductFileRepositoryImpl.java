package ua.com.ex.reprository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import ua.com.ex.model.Product;
import ua.com.ex.reprository.ProductFileRepository;
import ua.com.ex.reprository.impl.file.Import;

@Repository("productFileRepository")
public class ProductFileRepositoryImpl implements ProductFileRepository {

    private static final int COLUMN_COLOR = 25;
    private static final int COLUMN_SIZE = 24;
    private static final int COLUMN_ENABLED = 16;
    private static final int COLUMN_CATEGORY_ID = 3;
    private static final int COLUMN_COUNT = 22;
    private static final int COLUMN_PRICE = 17;
    private static final int COLUMN_DISCOUNT = 18;
    private static final int COLUMN_NAME = 13;
    private static final int COLUMN_ID = 0;
    @Override
    public ArrayList<Product> getAll() {
        Import importProduct = new Import();
        ArrayList<ArrayList<String>> products = importProduct.get("INSERT INTO `products` VALUES", "UNLOCK TABLES", "localexbase.sql", 46);
        ArrayList<Product> result = new ArrayList<>();
        for (ArrayList<String> current: products){
            if (isNeed(current)){
                result.add(mapperProduct(current));
            }
        }
        return result;
    }
    private boolean isNeed(ArrayList<String> current) {
        int enabled = 0;
        try{
            enabled = (int) Integer.parseInt(current.get(COLUMN_ENABLED));                      
        } catch (Exception e) {
            e.printStackTrace();   
        }
        return enabled == 1;
    }
    private Product mapperProduct(ArrayList<String> fields){
        Product result = new Product();
        try{
            int id = (int) Integer.parseInt(fields.get(COLUMN_ID));
            result.setId(id); 
            String name = fields.get(COLUMN_NAME);
            result.setName(name);
            BigDecimal price = new BigDecimal(fields.get(COLUMN_PRICE));
            result.setPrice(price);
            int discount = (int) Integer.parseInt(fields.get(COLUMN_DISCOUNT));
            result.setDiscount(discount);
            int quantity = (int) Integer.parseInt(fields.get(COLUMN_COUNT));
            result.setQuantity(quantity);
            int categoryId = (int) Integer.parseInt(fields.get(COLUMN_CATEGORY_ID));
            result.setCategoryId(categoryId);            
            String size = fields.get(COLUMN_SIZE);            
            if(!size.equals("NULL")){
                result.setSize(size);
            }else{
                result.setSize("");
            }
            String color = fields.get(COLUMN_COLOR);
            if(!color.equals("NULL")){
                result.setColor(color);
            } else{
                result.setColor("");
            }
            result.setCategoryId(categoryId); 

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;        
    }
}

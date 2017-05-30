package ua.com.ex.model.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import ua.com.ex.model.Product;

@Component("productMapper")
public class ProductMapperImpl implements ProductMapper {

    private static final int COLUMN_GROUP_ID = 5;

    private static final int COLUMN_COLOR = 25;
    private static final int COLUMN_SIZE = 24;

    private static final int COLUMN_CATEGORY_ID = 3;
    private static final int COLUMN_COUNT = 22;
    private static final int COLUMN_PRICE = 17;
    private static final int COLUMN_DISCOUNT = 18;
    private static final int COLUMN_NAME = 13;
    private static final int COLUMN_ID = 0;

    @Override
    public Product getProduct(ArrayList<String> fields) {
        Product result = new Product();
        try{

            setId(fields, result); 
            setName(fields, result);
            setPrice(fields, result);
            setDiscount(fields, result);
            setQuantity(fields, result);                    
            setSize(fields, result);
            setColor(fields, result);
            setGroupId(fields, result);
            setCategoryId(fields, result);  
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;        
    }

    private void setCategoryId(ArrayList<String> fields, Product result) throws NumberFormatException {
        int categoryId = (int) Integer.parseInt(fields.get(COLUMN_CATEGORY_ID));
        result.setCategoryId(categoryId);
    }

    private void setGroupId(ArrayList<String> fields, Product result) throws NumberFormatException {     
        int groupId = (int) Integer.parseInt(fields.get(COLUMN_GROUP_ID));       
        result.setGroupId(groupId);
    }

    private void setColor(ArrayList<String> fields, Product result) {
        String color = fields.get(COLUMN_COLOR);
        if(!color.equals("NULL")){
            result.setColor(color);
        } else{
            result.setColor("");
        }
    }

    private void setSize(ArrayList<String> fields, Product result) {
        String size = fields.get(COLUMN_SIZE);            
        if(!size.equals("NULL")){
            result.setSize(size);
        }else{
            result.setSize("");
        }
    }   

    private void setQuantity(ArrayList<String> fields, Product result) throws NumberFormatException {
        int quantity = (int) Integer.parseInt(fields.get(COLUMN_COUNT));
        result.setQuantity(quantity);
    }

    private void setDiscount(ArrayList<String> fields, Product result) throws NumberFormatException {
        int discount = (int) Integer.parseInt(fields.get(COLUMN_DISCOUNT));
        result.setDiscount(discount);
    }

    private void setPrice(ArrayList<String> fields, Product result) {
        BigDecimal price = new BigDecimal(fields.get(COLUMN_PRICE));
        result.setPrice(price);
    }

    private void setName(ArrayList<String> fields, Product result) {
        String name = fields.get(COLUMN_NAME);
        result.setName(name);
    }

    private void setId(ArrayList<String> fields, Product result) throws NumberFormatException {
        int id = (int) Integer.parseInt(fields.get(COLUMN_ID));
        result.setId(id);
    }

}

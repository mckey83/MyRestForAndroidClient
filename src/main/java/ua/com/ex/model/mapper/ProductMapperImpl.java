package ua.com.ex.model.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import ua.com.ex.model.Product;

@Component("productMapper")
public class ProductMapperImpl implements ProductMapper {

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

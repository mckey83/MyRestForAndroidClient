package ua.com.ex.reprository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import ua.com.ex.model.Product;
import ua.com.ex.reprository.ProductFileRepository;
import ua.com.ex.reprository.impl.file.ImportProduct;

@Repository("productFileRepository")
public class ProductFileRepositoryImpl implements ProductFileRepository {

    @Override
    public ArrayList<Product> getAll() {
        ImportProduct importProduct = new ImportProduct();

        ArrayList<ArrayList<String>> products = importProduct.get("INSERT INTO `products` VALUES", "UNLOCK TABLES", "localexbase.sql");
        ArrayList<Product> result = new ArrayList<>();
        for (ArrayList<String> current: products){
            result.add(mapperProduct(current));
        }
        return result;
    }
    private Product mapperProduct(ArrayList<String> fields){
        Product result = new Product();
        int id = (int) Integer.parseInt(fields.get(0));
        result.setId(id);       
        result.setName(fields.get(1));
        result.setPrice(new BigDecimal(fields.get(2)));
        int quantity = (int) Integer.parseInt(fields.get(3));
        result.setQuantity(quantity);
        int categoryId = (int) Integer.parseInt(fields.get(4));
        result.setCategoryId(categoryId);        
        return result;        
    }
}

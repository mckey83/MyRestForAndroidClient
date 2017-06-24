package ua.com.ex.model.view;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.ex.model.Product;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCardItem {
    
    public ProductCardItem(Product p){
        this.id = p.getId();        
        this.price = p.getPrice();
        this.discount = p.getDiscount();
        this.quantity = p.getQuantity();
        this.color = p.getColor();
        this.size = p.getSize();         
    }
    
    private int id ;
    
    private BigDecimal price ;
    
    private int discount;  
    
    private int quantity;
    
    private String color ;

    private String size ;   
    
    @Override
    public String toString() {
        return "ProductCardItem [id=" + id + ", price=" + price + ", discount=" + discount + ", quantity=" + quantity + ", color=" + color + ", size=" + size + "]";
    }
    
}

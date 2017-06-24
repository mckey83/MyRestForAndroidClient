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
public class ProductCatalogItem {   

    public ProductCatalogItem(Product p){
       this.id = p.getId();
       this.name = p.getName();
       this.price = p.getPrice();
       this.discount = p.getDiscount();        
       this.quantity = p.getQuantity();      
       this.color = p.getColor();      
       this.size = p.getSize();   
       this.imageMain = p.getImageMain();
    }   
    
    private int id;
    
    private String name;
    
    private BigDecimal price;
    
    private int discount;    
   
    private int quantity;
   
    private String color;
   
    private String size; 
    
    private String imageMain;   
    
    @Override
    public int hashCode() {
        return 31 + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductCatalogItem other = (ProductCatalogItem) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "ProductCatalogItem [id=" + id + ", name=" + name + ", price=" + price + ", discount=" + discount + ", quantity=" + quantity + ", color=" + color + ", size=" + size
                 + "]";
    }
    
}

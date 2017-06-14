package ua.com.ex.model.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.ex.model.Product;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCard {    
   	
	public ProductCard(Product parent, List<Product> children){
		this.id = parent.getId();
	    this.name = parent.getName();
		this.price = parent.getPrice();
		this.discount = parent.getDiscount();
		this.quantity = parent.getQuantity();
		this.color = parent.getColor();
		this.size = parent.getSize();
		this.imageProductCardMain = parent.getImageBase64();
		this.children = new ArrayList<>();
		for(Product p : children){
		    this.children.add(new ProductCardItem(p));
		}
	}
	
	private int id ;  
	
	private String name ;	

	private BigDecimal price ;

	private int discount;    

	private int quantity;
	
	private String color ;

    private String size ; 

	private String imageProductCardMain = "";	
	
	private List<ProductCardItem> children ;

    @Override
    public String toString() {
        String productCardItem ="\n";
        for(ProductCardItem p : children){
            productCardItem += "    "+p.toString() + "\n" ;
        }        
        return "ProductCard [id=" + id + ", name=" + name + ", price=" + price + ", discount=" + discount + ", quantity=" + quantity + ", color=" + color + ", size=" + size
                + ", children :" + productCardItem + "]";
    }
}

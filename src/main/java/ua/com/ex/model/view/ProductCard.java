package ua.com.ex.model.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		this.children = new ArrayList<>();
		this.images = new HashMap<>();		
		this.images.put(parent.getColor(), parent.getImageMain());
		for(Product p : children){
			this.children.add(new ProductCardItem(p));
			String colorP = p.getColor();
			String imageP = p.getImageMain();
			if(!colorP.isEmpty() && imageP.length() > 1004){
				this.images.put(p.getColor(), p.getImageMain());
			}
		}
	}

	private int id ;  

	private String name ;	

	private BigDecimal price ;

	private int discount;    

	private int quantity;

	private String color ;

	private String size ; 		

	private List<ProductCardItem> children ;

	private Map<String, String> images ;

	@Override
	public String toString() {
		String productCardItem ="\n";
		for(ProductCardItem p : children){
			productCardItem += "    "+p.toString() + "\n" ;
		}  
		String imagesKeyValue ="";
		for (Map.Entry<String, String> entry : images.entrySet()) {
			imagesKeyValue += "Key: " + entry.getKey() + " Value: "
					+ entry.getValue().length();
		}
		return "ProductCard [id=" + id + ", name=" + name + ", price=" + price + ", discount=" + discount + 
				", quantity=" + quantity 
				+ ", color=" + color 
				+ ", color image size =" +images.get(color).length()
				+ ", size=" + size        		
				+", imagesKeyValue="+imagesKeyValue
				+ ", children :" + productCardItem + "]";
	}
}

package ua.com.ex.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id @Column(name = "ID")
	private int id;	

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "DISCOUNT")
    private int discount;		

    @Column(name = "PARENT_CATEGORY")
    private int categoryId;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "SIZE")
    private String size;
    
    @Column(name = "GROUP_ID", columnDefinition = "TINYINT")
    private int groupId;

    @Transient
    private String imageBase64;

    @Transient
    @Override
    public int hashCode() {       
        return id + 31;
    }

    @Transient
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id != other.id)
            return false;       
        return true;
    }

}

package ua.com.ex.model;

import java.io.Serializable;

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
@Table(name = "CATEGORIES")
public class Category implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PARENT_ID")
    private int parentId;    
    
    @Transient
    private String imageBase64;
    
    @Column(name = "PRODUCT_QUANTITY")
    private int productQuantity;

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
        Category other = (Category) obj;
        if (id != other.id)
            return false;
        if (imageBase64 == null) {
            if (other.imageBase64 != null)
                return false;
        } else if (!imageBase64.equals(other.imageBase64))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (parentId != other.parentId)
            return false;
        if (productQuantity != other.productQuantity)
            return false;
        return true;
    }

    
   



    

  


}

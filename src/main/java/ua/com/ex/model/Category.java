package ua.com.ex.model;

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
@Table(name = "categories")
public class Category {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private int parentId;
    
    @Column(name = "extra",  columnDefinition = "BIT")
    private byte extra;    
    
    @Column(name = "enabled",  columnDefinition = "BIT")    
    private byte enabled;
    
    @Transient
    private String imageBase64;
    
    @Transient
    private int quantityOfProducts;

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
        if (quantityOfProducts != other.quantityOfProducts)
            return false;
        return true;
    }



    

  


}

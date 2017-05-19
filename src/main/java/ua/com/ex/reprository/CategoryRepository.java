package ua.com.ex.reprository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.ex.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

    @Query("SELECT c FROM Category c WHERE c.parentId = ?1")
    public List<Category> getCategoryByParentId(int parentId);
    
    @Query("SELECT COUNT(p.id) FROM Product p WHERE p.categoryId = ?1")
    int findProductQuantityByCategoryId(int categoryId);
    
}

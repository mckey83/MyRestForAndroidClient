package ua.com.ex.reprository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.ex.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
	@Query("FROM Product p WHERE p.categoryId = ?1 ORDER BY p.name")
	List<Product> findProductByCategoryIdPagination(Pageable page, int categoryId);
	
	@Query("FROM Product p WHERE p.categoryId = ?1 ORDER BY p.name")
    List<Product> findProductByCategoryId(int categoryId);
	
	@Query("FROM Product p WHERE p.categoryId = ?1 and p.id = p.groupId ORDER BY p.name")
    List<Product> findProductParentOnlyByCategoryId(int categoryId);
	
	@Query("FROM Product p WHERE p.categoryId = ?1 and p.id = p.groupId ORDER BY p.name")
    List<Product> findProductParentOnlyByCategoryIdPagination(Pageable page, int categoryId);
	
	@Query("FROM Product p WHERE p.groupId = ?1 ORDER BY p.name")
    List<Product> findProductByGroupId(int groupId);
	
	
}

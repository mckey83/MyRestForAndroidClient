package ua.com.ex.reprository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.ex.model.Product;

public interface ProductReprository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

	@Query("FROM Product p WHERE p.categoryId = ?1")
	List<Product> findProductByCategoryId(int id);
}
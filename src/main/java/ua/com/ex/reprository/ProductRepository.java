package ua.com.ex.reprository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import ua.com.ex.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
	@Query("FROM Product p WHERE p.categoryId = ?1")
	List<Product> findProductByCategoryId(Pageable page, int categoryId);
	
	
}

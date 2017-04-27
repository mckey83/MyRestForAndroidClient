package ua.com.ex.reprository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.ex.model.Product;

public interface ProductReprository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

}

package ua.com.ex.reprository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.ex.model.Category;

public interface CategoryReprository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

}

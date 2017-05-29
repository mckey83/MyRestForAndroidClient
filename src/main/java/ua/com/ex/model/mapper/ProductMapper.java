package ua.com.ex.model.mapper;

import java.util.ArrayList;

import ua.com.ex.model.Product;

public interface ProductMapper {
   Product getProduct (ArrayList<String> fields);
}

package ua.com.ex.reprository;

import java.util.ArrayList;

import ua.com.ex.model.Category;

public interface CategoryFileRepository {
    ArrayList<Category> getAll();
}

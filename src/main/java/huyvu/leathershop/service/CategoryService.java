package huyvu.leathershop.service;

import huyvu.leathershop.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CategoryService {

    List<Category> getAllCategories();
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(String id);
    Optional<Category> getCategory(String id);

}

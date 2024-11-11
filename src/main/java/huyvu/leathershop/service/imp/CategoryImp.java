package huyvu.leathershop.service.imp;

import huyvu.leathershop.model.Category;
import huyvu.leathershop.repository.CategoryRepository;
import huyvu.leathershop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(String id) {
        if (categoryRepository.existsById(id)) categoryRepository.deleteById(id);
        else throw new RuntimeException("Category not found");

    }

    @Override
    public Optional<Category> getCategory(String id) {
        return categoryRepository.findById(id);
    }
}

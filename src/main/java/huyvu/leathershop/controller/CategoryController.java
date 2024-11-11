package huyvu.leathershop.controller;

import huyvu.leathershop.model.Category;
import huyvu.leathershop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("list/")
    public ResponseEntity<List<Category>> getAllCategories() {

        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("save/")
    public ResponseEntity<Category> saveCategorry(@RequestBody Category category) {
        Category savedProduct = categoryService.createCategory(category);
        return ResponseEntity.ok(savedProduct);
    }
    @DeleteMapping("detete/{id}")
    public ResponseEntity<Integer> deteteCategory(@PathVariable int id) {
        return ResponseEntity.ok(200);
    }
}


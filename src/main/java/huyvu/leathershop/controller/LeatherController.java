package huyvu.leathershop.controller;


import huyvu.leathershop.model.Category;
import huyvu.leathershop.model.Leather;
import huyvu.leathershop.service.CategoryService;
import huyvu.leathershop.service.LeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/leather/")
public class LeatherController {
    @Autowired
    private LeatherService leatherService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("list/")
    public ResponseEntity<List<Leather>> getAllLeather() {

        return ResponseEntity.ok(leatherService.getAllLeathers());
    }
    @PostMapping("save/")
    public ResponseEntity<?> addLeather(@RequestBody Leather leather) {
        // Validate and find the category
        if (leather.getCategory() == null || leather.getCategory().getId() == null) {
            return ResponseEntity.badRequest().body("Category ID is required.");
        }

        Optional<Category> categoryOptional = categoryService.getCategory(leather.getCategory().getId());
        if (categoryOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Category not found.");
        }

        // Set the category object from the database
        leather.setCategory(categoryOptional.get());

        // Save the Leather item
        Leather savedLeather = leatherService.createLeather(leather);

        return ResponseEntity.ok(savedLeather);
    }
}

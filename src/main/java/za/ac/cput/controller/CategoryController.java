package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Category;
import za.ac.cput.service.CategoryService;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:5119", maxAge = 3600)
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


//    @PostMapping("/create")
//    public Category createCategory(@RequestBody Category category) {
//        return categoryService.create(category);
//    }
@PostMapping("/create")
public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    Category createdCategory = categoryService.create(category);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
}
    @GetMapping("/read/{id}")
    public Category readCategory(@PathVariable Long id) {
        return categoryService.read(id);
    }

    @PutMapping("/update")
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.update(category);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getAll")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findCategoryByName")
    public Category findCategoryByName(String name){
        return categoryService.findCategoryByName(name);
    }
}
/*

 */
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Product;
import za.ac.cput.service.AdminService;
import za.ac.cput.service.ProductService;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:5119", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping("/read/{id}")
    public Product read(@PathVariable Long id) {
        return productService.read(id);
    }

    @PutMapping("/update")
    public Product update(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getAll")
    public Set<Product> getAll() {
        return productService.getAll();
    }
}
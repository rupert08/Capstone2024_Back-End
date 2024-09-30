package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Product;
import za.ac.cput.service.AdminService;
import za.ac.cput.service.ProductService;

import java.util.List;
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
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return new ResponseEntity<>(productService.create(product), HttpStatus.OK);
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
    public ResponseEntity<List<Product>> getAll() {

        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/addProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile image) {
        try {
            Product product1 = productService.addProduct(product, image);
            return new ResponseEntity<>(product1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        byte[] image = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(image);
    }
}
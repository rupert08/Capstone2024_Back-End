package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;
import za.ac.cput.service.AdminService;
import za.ac.cput.service.CustomerService;
import za.ac.cput.service.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:5119", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return new ResponseEntity<>(productService.create(product), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public Product read(@PathVariable Long id) {
        return productService.read(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable long id,
                                         @RequestPart("product") Product product,
                                         @RequestPart(value = "image", required = false) MultipartFile image) {
        Product product1 ;

        try {
            product1 = productService.updateProduct(id, product, image);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update product", HttpStatus.PRECONDITION_FAILED);
        }
        if(product1 != null)
            return new ResponseEntity<>("Updated " + product1.getName(), HttpStatus.OK);
        else
            return new ResponseEntity<>("Product Not Found", HttpStatus.PRECONDITION_FAILED);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if(product != null) {
            productService.delete(id);
            return new ResponseEntity<>("Deleted " + product.getName(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product Not Found", HttpStatus.PRECONDITION_FAILED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getAll")
    public ResponseEntity<List<Product>> getAll() {

        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/addProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(@RequestPart("product") Product product,
                                        @RequestPart(value = "image", required = false) MultipartFile image) {
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
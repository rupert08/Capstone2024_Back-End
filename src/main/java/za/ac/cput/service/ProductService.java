package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Category;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.CategoryRepository;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.service.interfaces.IPaymentService;
import za.ac.cput.service.interfaces.IProductService;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }


    @Override
    public Product read(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductsByName(String name) {
        return productRepository.findProductsByName(name);
    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {
        // Fetch the category from the database using the categoryId from the product object
        //if (product.getCategory() != null && product.getCategory().getCategoryId() != null) {
        Category category = categoryRepository.findById(product.getCategory().getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + product.getCategory().getCategoryId()));
        product.setCategory(category);  // Set the managed category entity
        //}

        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());

        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(long id, Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());

        return productRepository.save(product);
    }
}
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.service.interfaces.IPaymentService;
import za.ac.cput.service.interfaces.IProductService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Autowired
    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public Set<Product> getAll() {
        return productRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Product findProductsByName(String name) {
        return productRepository.findProductsByName(name);
    }


    /* /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product if found, or null if not found.
     */
   /* @Override
    public Product read(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    /**
     * Updates an existing product.
     *
     * @param product The updated product object.
     * @return The updated product object.
     * @throws IllegalArgumentException If the product is null.
     */
    /*@Override
    public Product update(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     */
    /*@Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Retrieves all products.
     *
     * @return A set of all products.
     */
    /*@Override
    public Set<Product> getAll() {
        return new HashSet<>(productRepository.findAll());
    }
    */
}
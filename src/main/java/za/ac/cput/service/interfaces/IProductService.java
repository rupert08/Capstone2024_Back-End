package za.ac.cput.service.interfaces;

import za.ac.cput.domain.Product;

import java.util.Set;

public interface IProductService extends IService<Product, Long>{

    Set<Product> getAll();
    Product findProductsByName(String name);

}

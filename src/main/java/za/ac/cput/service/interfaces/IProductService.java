package za.ac.cput.service.interfaces;

import za.ac.cput.domain.Product;

import java.util.List;
import java.util.Set;

public interface IProductService extends IService<Product, Long>{

    List<Product> getAll();
    Product findProductsByName(String name);

}

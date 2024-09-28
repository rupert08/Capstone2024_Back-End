package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Shipping;
import za.ac.cput.repository.ShippingRepository;
import za.ac.cput.service.interfaces.IShippingService;

@Service
public class ShippingService implements IShippingService {
    ShippingRepository shippingRepository;

    @Autowired
    ShippingService(ShippingRepository shippingRepository) {this.shippingRepository = shippingRepository;}

    @Override
    public Shipping create(Shipping obj) {
        return shippingRepository.save(obj);
    }

    @Override
    public Shipping read(Long id) {
        return shippingRepository.findById(id).orElse(null);
    }

    @Override
    public Shipping update(Shipping obj) {
        return shippingRepository.save(obj);
    }

    @Override
    public void delete(Long id) {
        shippingRepository.deleteById(id);
    }
}

package za.ac.cput.service.interfaces;

import za.ac.cput.domain.Address;
import java.util.Set;

public interface IAddressService extends IService<Address, Long> {
    Set<Address> getAllAddresses();
}

package za.ac.cput.service.interfaces;

import za.ac.cput.domain.CartItem;

import java.util.Set;

public interface ICartItemService extends IService<CartItem, Long>{
    Set<CartItem> getAll();
}

package huyvu.leathershop.service.imp;

import huyvu.leathershop.model.CartItem;
import huyvu.leathershop.repository.CartItemRepository;
import huyvu.leathershop.repository.CartRepository;
import huyvu.leathershop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartImp implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Override
    public CartItem addCartItem(CartItem cartItem) {
        return null;
    }
}

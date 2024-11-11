package huyvu.leathershop.repository;

import huyvu.leathershop.model.Cart;
import huyvu.leathershop.model.CartItem;
import huyvu.leathershop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, String> {


}






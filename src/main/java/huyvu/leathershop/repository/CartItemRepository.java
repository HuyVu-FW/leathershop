package huyvu.leathershop.repository;

import huyvu.leathershop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
}

package huyvu.leathershop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@Entity
@Data

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generate ID
    @Column(updatable = false)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
}


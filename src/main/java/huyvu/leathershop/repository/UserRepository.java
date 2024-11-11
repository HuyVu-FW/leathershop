package huyvu.leathershop.repository;

import huyvu.leathershop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserName( final String username );

// Optional giúp kiểm tra null khỏe hơn
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String userName);




}

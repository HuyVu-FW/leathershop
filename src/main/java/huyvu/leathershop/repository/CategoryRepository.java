package huyvu.leathershop.repository;

import huyvu.leathershop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, String> {
}

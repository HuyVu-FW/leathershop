package huyvu.leathershop.repository;

import huyvu.leathershop.model.Leather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LeatherRepository  extends JpaRepository<Leather, String> {
}

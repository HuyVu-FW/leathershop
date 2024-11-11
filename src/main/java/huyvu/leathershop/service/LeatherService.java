package huyvu.leathershop.service;

import huyvu.leathershop.model.Leather;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface LeatherService {

    List<Leather> getAllLeathers();
    Leather createLeather(Leather leather);
    Leather updateLeather(Leather leather);
    void deleteLeather(UUID id);
    Optional<Leather> getCategory(UUID id);
}

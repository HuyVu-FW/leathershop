package huyvu.leathershop.service.imp;

import huyvu.leathershop.model.Leather;
import huyvu.leathershop.repository.LeatherRepository;
import huyvu.leathershop.service.LeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LeatherImp implements LeatherService {

    @Autowired
    private LeatherRepository leatherRepository;

    @Override
    public List<Leather> getAllLeathers() {
        return leatherRepository.findAll();
    }

    @Override
    public Leather createLeather(Leather leather) {
        return leatherRepository.save(leather);
    }

    @Override
    public Leather updateLeather(Leather leather) {
        return null;
    }

    @Override
    public void deleteLeather(UUID id) {

    }

    @Override
    public Optional<Leather> getCategory(UUID id) {
        return Optional.empty();
    }
}

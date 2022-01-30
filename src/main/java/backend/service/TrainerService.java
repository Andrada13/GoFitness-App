package backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Trainer;
import backend.repository.TrainerRepository;

@Service
public class TrainerService {

    @Autowired
    TrainerRepository trainerRepository;

    public void updateTrainers(long id, String name, String email, String type) {
        Trainer trainer = findTrainerById(id);
        trainer.setName(name);
        trainer.setEmail(email);
        trainer.setType(type);
        trainerRepository.save(trainer);
    }

    public Trainer findTrainerById(long id) {
        for (Trainer trainer : findAllTrainers()) {
            if (trainer.getId() == id) {
                return trainer;
            }
        }

        return null;
    }

    public List<Trainer> findAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        trainerRepository.findAll().forEach(trainers::add);
        return trainers;
    }

}

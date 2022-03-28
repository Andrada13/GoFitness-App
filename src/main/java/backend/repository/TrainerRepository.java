package backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.models.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
	
	Optional<Trainer> findByUsername(String username) ;
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);


}


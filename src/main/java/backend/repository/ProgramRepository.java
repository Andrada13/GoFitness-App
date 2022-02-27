package backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.models.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
	
	Optional<Program> findByName(String name) ;
	Boolean existsByName(String name);


}


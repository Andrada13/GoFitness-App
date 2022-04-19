package backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.models.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
	
	//Program findByName(String name) ;
	Boolean existsByName(String name);


}


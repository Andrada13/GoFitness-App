package backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.models.ProgramTime;

@Repository
public interface ProgramTimeRepository extends JpaRepository<ProgramTime, Long> {
	



}


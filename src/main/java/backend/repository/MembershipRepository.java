package backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.models.Memberships;

@Repository
public interface MembershipRepository extends JpaRepository<Memberships, Long> {
	
	Optional<Memberships> findByName(String name) ;
	Boolean existsByName(String name);


}


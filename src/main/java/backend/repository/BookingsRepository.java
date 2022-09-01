package backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.models.Bookings;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {
	

}


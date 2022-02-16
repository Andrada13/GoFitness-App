package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.Messages;

public interface MessageRepository extends JpaRepository<Messages,Long>{
    
}

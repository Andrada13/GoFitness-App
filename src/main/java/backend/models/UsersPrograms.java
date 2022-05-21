package backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users_programs")
public class UsersPrograms {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    
    @Column(name = "trainer_id")
    Long trainerId;


    @Column(name = "programs_id")
    Long programsId;

    

    public UsersPrograms(Long trainerId, Long programsId) {
        this.trainerId = trainerId;
        this.programsId = programsId;
    }


    public UsersPrograms() {
    }


    public Long getTrainerId() {
        return trainerId;
    }
    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }
    public Long getProgramsId() {
        return programsId;
    }
    public void setProgramsId(Long programsId) {
        this.programsId = programsId;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    
    
}

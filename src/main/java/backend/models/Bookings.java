
package backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(	name = "bookings")
public class Bookings {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userid;

	private Long courseid;

    private Long grupa;


    @ColumnDefault("false")
	private Boolean paid;

	private Long trainerid;

	private Long timeid;


	private Boolean canceled;


   
    @ManyToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<User> user = new ArrayList<>();

    @ManyToMany(mappedBy = "courseId", cascade = CascadeType.ALL)
    private List<Program> course = new ArrayList<>();

    @ManyToMany(mappedBy = "trainerId", cascade = CascadeType.ALL)
    private List<User> trainer = new ArrayList<>();

    @ManyToMany(mappedBy = "timeId", cascade = CascadeType.ALL)
    private List<ProgramTime> time = new ArrayList<>();

    public Bookings() {
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  

    public Long getUserId() {
        return userid;
    }

    public void setUserId(Long userid) {
        this.userid = userid;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

   

    public Long getCourseId() {
        return courseid;
    }



    public void setCourseId(Long courseid) {
        this.courseid = courseid;
    }



    public Long getTrainerId() {
        return trainerid;
    }



    public void setTrainerId(Long trainerid) {
        this.trainerid = trainerid;
    }



    public Long getTimeId() {
        return timeid;
    }



    public void setTimeId(Long timeid) {
        this.timeid = timeid;
    }



   


    public Boolean getCanceled() {
        return canceled;
    }



    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }



    public Long getGroup() {
        return grupa;
    }



    public void setGroup(Long grupa) {
        this.grupa = grupa;
    }



    public List<User> getUser() {
        return user;
    }



    public void setUser(List<User> user) {
        this.user = user;
    }



    public List<Program> getCourse() {
        return course;
    }



    public void setCourse(List<Program> course) {
        this.course = course;
    }



    public List<User> getTrainer() {
        return trainer;
    }



    public void setTrainer(List<User> trainer) {
        this.trainer = trainer;
    }



    public List<ProgramTime> getTime() {
        return time;
    }



    public void setTime(List<ProgramTime> time) {
        this.time = time;
    }

    
	

}

package backend.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "program_time")
public class ProgramTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programTime_id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
    @JsonIgnore
    Program program;

    private String time_program;




	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(	name = "time_bookings", 
				joinColumns = @JoinColumn(name = "id"), 
				inverseJoinColumns = @JoinColumn(name = "time_id"))
	@JsonIgnore
    private List<Bookings> timeId = new ArrayList<>();
    
    public ProgramTime() {
    }

    
    public ProgramTime( String time_program, Program program) {
        this.program = program;
        this.time_program = time_program;
    }


    public ProgramTime(Long programTime_id, Program program, String time_program) {
        this.programTime_id = programTime_id;
        this.program = program;
        this.time_program = time_program;
    }

    public Long getProgramTime_id() {
        return programTime_id;
    }

    public void setProgramTime_id(Long programTime_id) {
        this.programTime_id = programTime_id;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }


    public String getTime_program() {
        return time_program;
    }


    public void setTime_program(String time_program) {
        this.time_program = time_program;
    }
    
}

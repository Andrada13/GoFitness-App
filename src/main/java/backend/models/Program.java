package backend.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "programs", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
})
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "program", cascade = CascadeType.MERGE)
   // @JsonBackReference
    private List<ProgramTime> programs = new ArrayList<>();

    @NotBlank
    private String description;
    
    @ManyToMany(mappedBy = "programs", cascade = {
              CascadeType.PERSIST,
             CascadeType.MERGE
      })
    public List<User> trainer = new ArrayList<>();

    @NotBlank
    private String price;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	//@JoinColumn(name = "courseId")
    @JoinTable(	name = "programs_bookings", 
				joinColumns = @JoinColumn(name = "id"), 
				inverseJoinColumns = @JoinColumn(name = "program_id"))
	//@JsonManagedReference
	@JsonIgnore
    private List<Bookings> courseId = new ArrayList<>();



    public List<ProgramTime> getPrograms() {
        return programs;
    }

    public void setPrograms(List<ProgramTime> programs) {
        this.programs = programs;
    }

    public Program(Long id, @NotBlank @Size(max = 50) String name, @NotBlank List<ProgramTime> programs,
            @NotBlank @Size(max = 50) String description,  @NotBlank String price, @NotBlank List<User> trainer) {
        this.id = id;
        this.name = name;
        this.programs = programs;
        this.description = description;
        this.price = price;
      //  this.trainer = trainer;
    }

    public Program(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 50) String description, @NotBlank String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Program() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void remove() {
        for(User trainerEntity : new ArrayList<>(trainer)) {
            trainer.remove(trainerEntity);
            trainerEntity.getPrograms().remove(this);
        }
    }

   
   

}

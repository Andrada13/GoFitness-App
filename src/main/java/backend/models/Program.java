package backend.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "programs", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
})
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<ProgramTime> programs=new ArrayList<>();


    @NotBlank
    @Size(max = 50)
    private String description;

    @NotBlank
    @Size(max = 50)
    private String trainerName;

    @NotBlank
    private String price;


    public List<ProgramTime> getPrograms() {
        return programs;
    }



    public void setPrograms(List<ProgramTime> programs) {
        this.programs = programs;
    }





    public Program(Long id, @NotBlank @Size(max = 50) String name, @NotBlank List<ProgramTime> programs,
            @NotBlank @Size(max = 50) String description, @NotBlank @Size(max = 50) String trainerName,
            @NotBlank String price) {
        this.id = id;
        this.name = name;
        this.programs = programs;
        this.description = description;
        this.trainerName = trainerName;
        this.price = price;
    }



    public Program(@NotBlank @Size(max = 50) String name,
            @NotBlank @Size(max = 50) String description, @NotBlank @Size(max = 50) String trainerName,
            @NotBlank String price) {
        this.name = name;
        this.description = description;
        this.trainerName = trainerName;
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

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}

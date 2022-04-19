package backend.requests;

import java.util.List;

import javax.validation.constraints.*;


import backend.models.ProgramTime;
 
public class AddProgramRequest {
    
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String description;
    
    //@NotBlank
    private List<ProgramTime> programs;

    @NotBlank
    private String price;
    


    
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


    public List<ProgramTime> getPrograms() {
        return programs;
    }

    public void setPrograms(List<ProgramTime> programs) {
        this.programs = programs;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    
}

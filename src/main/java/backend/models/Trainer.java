package backend.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(	name = "trainers", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "name"),
			@UniqueConstraint(columnNames = "email") 
		})
public class Trainer {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String name;
    
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 50)
	private String type;

    public Trainer(Long id, String name, String email, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public Trainer(String name, String email, String type) {
        this.name = name;
        this.email = email;
        this.type = type;
    }




    public Trainer(){

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


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }

    
    
}

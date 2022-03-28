package backend.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(	name = "trainers", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class Trainer {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String fullName;

    @NotBlank
	@Size(max = 50)
	private String username;
    
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

    @NotBlank
	@Size(max = 120)
	private String password;

	@NotBlank
	@Size(max = 50)
	private String description;

    @NotBlank
	@Size(min=10,max = 10)
	private String phoneNumber;

    @NotBlank
	@Size(max = 50)
	private String address;


    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "trainer_roles", 
				joinColumns = @JoinColumn(name = "id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
    @JsonIgnore
    private List<Program> course = new ArrayList<>();


    public Trainer(@NotBlank @Size(max = 50) String fullName, @NotBlank @Size(max = 50) String username,
            @NotBlank @Size(max = 50) @Email String email, @NotBlank
            @Size(max = 50) String description,@NotBlank @Size(max = 120) String password,
            @NotBlank @Size(min = 10, max = 10) String phoneNumber, @NotBlank @Size(max = 50) String address) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.description =description;
    }




    public String getFullName() {
        return fullName;
    }




    public void setFullName(String fullName) {
        this.fullName = fullName;
    }




    public String getUsername() {
        return username;
    }




    public void setUsername(String username) {
        this.username = username;
    }



/*
    public Trainer(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }


*/

    public Trainer(){

    }

    

    

    public Set<Role> getRoles() {
        return roles;
    }




    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public Long getId() {
        return id;
    }




    public void setId(Long id) {
        this.id = id;
    }




    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


   

    
    
}

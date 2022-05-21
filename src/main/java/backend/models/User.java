package backend.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "full_name")
	private String fullName;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	//@Size(min=10,max = 10)
	@Pattern(regexp="[\\d]{10}", message="This field should contain 10 digits!")
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotBlank
	private String address;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = {
		      CascadeType.PERSIST,
		    CascadeType.MERGE
		  })
	// (fetch = FetchType.LAZY)
	// @JoinTable(name = "users_programs", 
	// 			joinColumns = @JoinColumn(name = "trainer_id", nullable = true), 
	// 			inverseJoinColumns = @JoinColumn(name = "programs_id", nullable = true))
//	@ManyToMany( fetch = FetchType.LAZY, cascade = {
  //      CascadeType.PERSIST,
  //      CascadeType.MERGE
  //  })
	@JoinColumn(name = "id")
	//@JsonManagedReference
	@JsonIgnore
    private List<Program> programs = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	//@JoinColumn(name = "id")
	//@JsonManagedReference
	@JoinTable(	name = "users_bookings", 
				joinColumns = @JoinColumn(name = "id"), 
				inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonIgnore
    private List<Bookings> userId = new ArrayList<>();


	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	//@JoinColumn(name = "trainerId")
	@JoinTable(	name = "trainers_bookings", 
				joinColumns = @JoinColumn(name = "id"), 
				inverseJoinColumns = @JoinColumn(name = "trainer_id"))
	//@JsonManagedReference
	@JsonIgnore
    private List<Bookings> trainerId = new ArrayList<>();

	
	public User() {
	}

	
    public User(Long id, @NotBlank @Size(max = 50) String fullName, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password,
			@NotBlank @Size(min = 10, max = 10) String phoneNumber, @NotBlank @Size(max = 70) String address,
			List<Role> roles) {
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.roles = roles;
	}




	public User(@NotBlank @Size(max = 50) String fullName, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password,
			@NotBlank @Size(min = 10,max = 10) String phoneNumber, @NotBlank @Size(max = 70) String address) {
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	


	public User(@NotBlank @Size(max = 50) String fullName, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password,
			@NotBlank @Size(min = 10, max = 10) String phoneNumber, @NotBlank @Size(max = 70) String address,
			List<Role> list,List<Program> programs) {
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.roles = list;
		this.programs  = programs;
	}




	




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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




	public List<Role> getRoles() {
		return roles;
	}




	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}




	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
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




	public List<Program> getPrograms() {
		return programs;
	}




	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	

}
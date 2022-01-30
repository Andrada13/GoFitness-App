package backend.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private UsersRoles name;


	public Role() {
	}

	public Role(UsersRoles name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UsersRoles getName() {
		return name;
	}

	public void setName(UsersRoles name) {
		this.name = name;
	}
}

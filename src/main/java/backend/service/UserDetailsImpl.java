package backend.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import backend.models.Program;
import backend.models.User;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String fullName;

	private String phoneNumber;
	private String address;


	private String email;


	@JsonIgnore
	private String password;

private List<Program> programs;

	//convert Set<Role> into List<GrantedAuthority>
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String fullName, String username, String email, String password, String phoneNumber, String address,
			Collection<? extends GrantedAuthority> authorities,List<Program> programs) {
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.authorities = authorities;
		this.programs = programs;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

			

		return new UserDetailsImpl(
				user.getId(), 
				user.getFullName(),
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				user.getPhoneNumber(), 
				user.getAddress(),
				authorities, user.getPrograms()
								);
	}


	


	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	
	//@Override
	public String getFullName() {
		return fullName;
	}

	//@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

//	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

   
}

package backend.requests;

import java.util.Set;

import javax.validation.constraints.*;
 
public class AddTrainerRequest {
    
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
    @Size(max = 50)
    private String description;


    @NotBlank
    @Size(max = 50)
    private String password;

    @NotBlank
    @Size(min=10,max = 10)
    private String phoneNumber;

    @NotBlank
    @Size(max = 50)
    private String address;

    private Set<String> role;


    
    
    public AddTrainerRequest(@NotBlank @Size(max = 50) String fullName, @NotBlank @Size(max = 50) String username,
            @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 50) String description,
            @NotBlank @Size(max = 50) String password, @NotBlank @Size(min = 10, max = 10) String phoneNumber,
            @NotBlank @Size(max = 50) String address) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.description = description;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    
    public Set<String> getRole() {
        return role;
    }



    public void setRole(Set<String> role) {
        this.role = role;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

package backend.requests;


import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonGetter;


 
public class SignupRequest {
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String fullName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;
    
    private Integer role;
    
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank
    @Pattern(regexp="[\\d]{10}", message="This field should contain 10 digits!")
    private String phoneNumber;

    @NotBlank
    @Size(max = 20)
    private String address;


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
    
 
   
   

    public String getFullName() {
        return fullName;
    }

    
   

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonGetter("role")
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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

package backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "memberships", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
})
public class Memberships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String description;

    @NotBlank
    private String price;

    @NotBlank
    private String date;

    public Memberships(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 50) String description,
            @NotBlank String price, @NotBlank String date) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
    }

    public Memberships(Long id, @NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 50) String description,
            @NotBlank String price, @NotBlank String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
    }

    public Memberships(){}

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    
    
}

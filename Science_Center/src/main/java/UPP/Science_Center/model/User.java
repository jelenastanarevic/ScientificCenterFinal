package UPP.Science_Center.model;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Length(max=30)
    @Column(nullable = false)
    private String first_name;

    @Length(max=40)
    @Column(nullable = false)
    private String last_name;

    @Length(max=50)
    @Column(nullable = false)
    private String city;

    @Length(max=50)
    @Column(nullable = false)
    private String country;

    @Length(max=50)
    @Column(nullable = false)
    private String email;

    @Length(max=20)
    @Column(nullable = false)
    private String password;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<ArticlesBought> articlesBought;

    public User() {
    }

    public User(@Length(max = 30) String first_name, @Length(max = 40) String last_name, @Length(max = 50) String city, @Length(max = 50) String country, @Length(max = 50) String email, @Length(max = 20) String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.city = city;
        this.country = country;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
}

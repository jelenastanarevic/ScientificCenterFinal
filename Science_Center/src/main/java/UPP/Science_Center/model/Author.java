package UPP.Science_Center.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;

@Entity
public class Author implements Serializable{
	
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
    
    //creator of an article
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Article> articles;

    //moze biti koautor u vise clanaka
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Coauthor> coauthors;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Membership> memberships;

    public Author() {
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



	public List<Membership> getMemberships() {
		return memberships;
	}



	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}



	public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Coauthor> getCoauthors() {
        return coauthors;
    }

    public void setCoauthors(List<Coauthor> coauthors) {
        this.coauthors = coauthors;
    }
}

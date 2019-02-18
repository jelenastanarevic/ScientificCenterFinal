package UPP.Science_Center.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;

@Entity(name="Editor")
public class Editor implements Serializable{
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
    
	private boolean headEditor;
	
	@OneToMany(mappedBy = "head_editor", cascade = CascadeType.REMOVE)
    private List<Magazine> magazines;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "scientific_field_id")
	    private ScientificField scientific;
	
	 @OneToMany(mappedBy = "editor", cascade = CascadeType.REMOVE)
	    private List<Article> coauthors;

	 
	public ScientificField getScientific_field() {
		return scientific;
	}


	public void setScientific_field(ScientificField scientific_field) {
		this.scientific = scientific_field;
	}


	public Editor(){}
	
	
	public Editor(boolean headEditor) {
		
		this.headEditor = headEditor;
	}


	public boolean isHeadEditor() {
		return headEditor;
	}

	public void setHeadEditor(boolean headEditor) {
		this.headEditor = headEditor;
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


	public List<Magazine> getMagazines() {
		return magazines;
	}


	public void setMagazines(List<Magazine> magazines) {
		this.magazines = magazines;
	}
	
	
	
	

}

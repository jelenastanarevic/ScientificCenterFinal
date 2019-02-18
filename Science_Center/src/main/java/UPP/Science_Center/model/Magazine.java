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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Magazine implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Length(max = 50)
    private String title;

    @NotNull
    @Column(nullable = false)
    @Length(min=9,max=9)
    private String issn;


    //tipovi placanja za svaki casopis - manyTomany klasa izmodelovana sa entitetom MagazinePaymentType
    @OneToMany(mappedBy = "magazine", cascade = CascadeType.REMOVE)
    private List<MagazinePaymentType> magazinePaymentTypes;


    @OneToMany(mappedBy = "magazine", cascade = CascadeType.REMOVE)
    private List<Membership> memberships;

    //artikli jednog casopisa
    @OneToMany(mappedBy = "magazine", cascade = CascadeType.REMOVE)
    private List<Article> articles;
    
  
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "head_editor_id")
    private Editor head_editor; 

    public Magazine(){}

    public Magazine(String title,String issn) {
        this.title = title;
        this.issn = issn;
    }
    
    
    

    public Editor getHead_editor() {
		return head_editor;
	}

	public void setHead_editor(Editor head_editor) {
		this.head_editor = head_editor;
	}

	public List<MagazinePaymentType> getMagazinePaymentTypes() {
		return magazinePaymentTypes;
	}

	public void setMagazinePaymentTypes(List<MagazinePaymentType> magazinePaymentTypes) {
		this.magazinePaymentTypes = magazinePaymentTypes;
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

	

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssn() {
        return issn;
    }


    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

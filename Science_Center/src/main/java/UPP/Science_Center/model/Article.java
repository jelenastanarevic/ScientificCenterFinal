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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;

@Entity
public class Article  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "editor_id")
    private Editor editor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User buyer;

    //vise koautora za jedan clanak
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Coauthor> coauthors;

    @Length(max = 300)
    @Column(nullable = false)
    private String title;
    
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<ArticlesBought> articlesBought;
    
    

    private String comment;
    
    
    
    
   

	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	private String keyWords;

    private String abstract_description;

    private Long working_version;

    private Long final_version;

    private boolean accepted;
    
    private Double price;

    //casopis kome pripada
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "magazine_id")
    private Magazine magazine;
    
    //sadrzaj i fajl
   /* @Lob
    @Column(name="CONTENT", length=512)
    private String content;
    */
    private String file_name;
    
    
    
    

    /*public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}*/

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	//clanak pripada 1 naucnoj oblasti
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scientific_field_id")
    private ScientificField scientific_field;


    public ScientificField getScientific_field() {
        return scientific_field;
    }

    public void setScientific_field(ScientificField scientific_field) {
        this.scientific_field = scientific_field;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public Article() {
        this.accepted = false;
    }
    

    public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	 @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
	    private List<Reviewer> reviewers;

	 
	public Article(@Length(max = 50) String title, String keyWords, String abstract_description, Long working_version, Long final_version, boolean accepted) {

        this.title = title;
        this.keyWords = keyWords;
        this.abstract_description = abstract_description;
        this.working_version = working_version;
        this.final_version = final_version;
        this.accepted = accepted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getAbstract_description() {
        return abstract_description;
    }

    public void setAbstract_description(String abstract_description) {
        this.abstract_description = abstract_description;
    }

    public Long getWorking_version() {
        return working_version;
    }

    public void setWorking_version(Long working_version) {
        this.working_version = working_version;
    }

    public Long getFinal_version() {
        return final_version;
    }

    public void setFinal_version(Long final_version) {
        this.final_version = final_version;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Coauthor> getCoauthors() {
		return coauthors;
	}

	public void setCoauthors(List<Coauthor> coauthors) {
		this.coauthors = coauthors;
	}
    
    
}

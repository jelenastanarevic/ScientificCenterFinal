package UPP.Science_Center.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class ScientificField implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max = 50)
    @Column(nullable = false)
    private String scientific_field_name;

    //vise clanaka pripada jednoj naucnoj oblasti
    @OneToMany(mappedBy = "scientific_field", cascade = CascadeType.REMOVE)
    private List<Article> articles;
    
    
    @OneToMany(mappedBy = "scientific", cascade = CascadeType.REMOVE)
    private List<Editor> editors;
    
    @OneToMany(mappedBy = "scientific", cascade = CascadeType.REMOVE)
    private List<Reviewer> reviewers;
    
   
    public ScientificField() {
    }

    public ScientificField(@Length(max = 50) String scientific_field_name) {
        this.scientific_field_name = scientific_field_name;
    }

    public Long getScientific_field_id() {
        return id;
    }

    public void setScientific_field_id(Long scientific_field_id) {
        this.id = scientific_field_id;
    }

    public String getScientific_field_name() {
        return scientific_field_name;
    }

    public void setScientific_field_name(String scientific_field_name) {
        this.scientific_field_name = scientific_field_name;
    }
}

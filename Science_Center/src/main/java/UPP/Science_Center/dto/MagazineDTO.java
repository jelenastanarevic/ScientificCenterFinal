package UPP.Science_Center.dto;



public class MagazineDTO {
	
    private Long id;
    private String title;
    private String issn;
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
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}

    public MagazineDTO(){}
	
    public MagazineDTO(Long id, String title, String issn) {
	
		this.id = id;
		this.title = title;
		this.issn = issn;
	}
    
    
}

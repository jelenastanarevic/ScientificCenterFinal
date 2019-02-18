package UPP.Science_Center.dto;


public class ArticleDTO {
	
	
    private String title;

    private String keyWords;

    private String abstract_description;
    
    private Long id_magazine;
    
    private String idTask;
    
    private String scientific_field_name;
    
    private String scientific_field_id;
    
    private Long id;
    
    private String author_name;
    
    private String file_name;
    
    private String processInstanceId;
    
    private Double price;
    
    private String comment;
    
    
    
    
    
    public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ArticleDTO(){}

	public ArticleDTO(String title, String keyWords, String abstract_description, Long id_magazine) {
	
		this.title = title;
		this.keyWords = keyWords;
		this.abstract_description = abstract_description;
		this.id_magazine = id_magazine;
	}
	
	
	
	

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public Long getId_magazine() {
		return id_magazine;
	}

	public void setId_magazine(Long id_magazine) {
		this.id_magazine = id_magazine;
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

	public String getIdTask() {
		return idTask;
	}

	public void setIdTask(String idTask) {
		this.idTask = idTask;
	}

	public String getScientific_field_name() {
		return scientific_field_name;
	}

	public void setScientific_field_name(String scientific_field_name) {
		this.scientific_field_name = scientific_field_name;
	}

	public String getScientific_field_id() {
		return scientific_field_id;
	}

	public void setScientific_field_id(String scientific_field_id) {
		this.scientific_field_id = scientific_field_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
	
    
    

}

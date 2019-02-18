package UPP.Science_Center.dto;

public class OrderDTO {

    private String magazineIssn;
    private String userEmail;
    private String articleTitle;
    private Double articlePrice;
    

    public OrderDTO() {
    }

    
    
   


	public Double getArticlePrice() {
		return articlePrice;
	}






	public void setArticlePrice(Double articlePrice) {
		this.articlePrice = articlePrice;
	}






	public String getMagazineIssn() {
        return magazineIssn;
    }

    
    public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public void setMagazineIssn(String magazineIssn) {
        this.magazineIssn = magazineIssn;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public OrderDTO(String magazineIssn, String userEmail) {

        this.magazineIssn = magazineIssn;
        this.userEmail = userEmail;
    }
}

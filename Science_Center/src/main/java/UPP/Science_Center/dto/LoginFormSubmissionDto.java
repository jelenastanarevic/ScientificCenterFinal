package UPP.Science_Center.dto;

import java.io.Serializable;

public class LoginFormSubmissionDto implements Serializable{
	String email;
	String password;
	
	public LoginFormSubmissionDto(){}
	
	public LoginFormSubmissionDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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

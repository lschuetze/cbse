package st.cbse.umeet.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Email;

public class LoginDetails {
	
	@Email
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String EMail){
		this.email = EMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

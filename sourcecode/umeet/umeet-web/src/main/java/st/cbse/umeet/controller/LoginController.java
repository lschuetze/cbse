package st.cbse.umeet.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import st.cbse.umeet.system.ILogin;


@ManagedBean
@SessionScoped
public class LoginController {
	
	@Email @NotEmpty String email;
	
	@Inject FacesContext facesContext;

	@NotEmpty private String password;

	@Inject ILogin loginBean;
	
	boolean isLoggedIn = false;

	public String getEmail(){
		return this.email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean getIsLoggedIn(){
		return isLoggedIn;
	}
	
	public String login(){
		if(!loginBean.login(email, password)){
			System.out.println("Login failed!");
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Username and password did not match"
					, "Login unsuccessful");
			facesContext.addMessage(null, m);
			isLoggedIn = false;
			return "login";
		}
		facesContext.getExternalContext().getSessionMap().put("user", email);
		isLoggedIn = true;
		return "showAppointments.xhtml";
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

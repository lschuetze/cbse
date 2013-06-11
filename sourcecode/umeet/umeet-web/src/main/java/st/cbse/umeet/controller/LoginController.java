package st.cbse.umeet.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import st.cbse.umeet.system.ILogin;


@ManagedBean
@SessionScoped
public class LoginController {
	
	@ManagedProperty(value = "")
	String email;
	
	@Inject
	FacesContext facesContext;

	@ManagedProperty(value = "")
	private String password;

	@Inject
	ILogin loginBean;

	public String getEmail(){
		return this.email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void login(){
		if(!loginBean.login(email, password)){
			System.out.println("Login Failed!");
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Username and password did not match"
					, "Login Unsuccessful");
			facesContext.addMessage(null, m);
		}
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

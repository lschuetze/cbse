package st.cbse.umeet.controller;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import st.cbse.umeet.system.UMeetSystem;


@ManagedBean
public class LoginController {
	
	private String email;
	
	private String password;
	
	@Inject
	FacesContext facesContext;
	
	@EJB
	UMeetSystem system;
	
	public void login(){
		if(!system.login(email, password)){
			System.out.println("Login Failed!");
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Username and password did not match"
					, "Login Unsuccessful");
			facesContext.addMessage(null, m);
		}
	}
	
	public String getEMail(){
		return this.email;
	}
}

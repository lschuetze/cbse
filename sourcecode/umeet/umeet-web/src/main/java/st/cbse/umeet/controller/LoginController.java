package st.cbse.umeet.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import st.cbse.umeet.dto.LoginDetails;
import st.cbse.umeet.system.ILogin;

@Model
public class LoginController {
	
	@Inject
	private FacesContext facesContext;

	@Inject
	private ILogin loginInterface;
	
	@Produces
	@Named
	private LoginDetails loginDetails;
	
	@PostConstruct
	public void initNewLoginDetails(){
		loginDetails = new LoginDetails();
	}
	
	public void login(){
		if(loginInterface.login(loginDetails.getEmail(), loginDetails.getPassword())){
			//TODO @Ronny DoLogin!
		} else {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Username and password did not match"
					, "Login Unsuccessful");
			facesContext.addMessage(null, m);
		}
	}
}

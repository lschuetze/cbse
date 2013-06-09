package st.cbse.umeet.controller;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import st.cbse.umeet.system.ILogin;

@Model
public class LoginController {
	
	@Inject
	private FacesContext facesContext;

	@Inject
	private ILogin loginInterface;
	
	public void login(String email, String password){
		if(loginInterface.login(email, password)){
			//TODO @Ronny DoLogin!
		} else {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Username and password did not match"
					, "Login Unsuccessful");
			facesContext.addMessage(null, m);
		}
	}
}

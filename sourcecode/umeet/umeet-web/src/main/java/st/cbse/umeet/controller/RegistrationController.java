/**
 * 
 */
package st.cbse.umeet.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import st.cbse.umeet.system.IRegisterUser;

/**
 * @author ronny
 *
 */
@ManagedBean
@RequestScoped
public class RegistrationController {
	
	@Email @NotEmpty String email;
	
	@NotEmpty String name;
	
	@NotEmpty String password;
	
	@Inject	FacesContext facesContext;
	
	@Inject	IRegisterUser registerBean;
	
	public String register(){
		if(!registerBean.registerUser(email, name, password)){
			System.out.println("Registration of the user " + name + " failed!");
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"A user with this email-address has already been registred."
					, "Registration unsuccessful");
			facesContext.addMessage(null, m);
			return "register";
		}
		return "login";
	}
	
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

package com.mb;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.facade.UserFacade;
import com.model.User;

/**
 * 
 * @author Lars Schuetze
 */

@ManagedBean
@SessionScoped
public class UserMB {
	
	@EJB
	private UserFacade userFacade;
	
	private User user;
	
	public User getUser() {
		if(user == null) {
			ExternalContext ctx = getContext().getExternalContext();
			String userEmail = ctx.getUserPrincipal().getName();
			user = userFacade.findUserByEmail(userEmail);
		}
		return user;
	}
	
	public boolean isAdmin() {
		return getRequest().isUserInRole("ADMIN");
	}
	
	public String logOut() {
		getRequest().getSession().invalidate();
		return "logout";
	}
	
	private FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest)getContext().getExternalContext().getRequest();
	}
}

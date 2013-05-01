package com.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.UserDAO;
import com.model.User;

/**
 * @author Lars Schuetze
 */

@Stateless
public class UserFacadeImpl implements UserFacade {
	
	@EJB
	private UserDAO userDAO;

	/* (non-Javadoc)
	 * @see com.facade.UserFacade#findUserByEmail(java.lang.String)
	 */
	@Override
	public User findUserByEmail(String email) {
		return userDAO.findUserByName(email);
	}

}

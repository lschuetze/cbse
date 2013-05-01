package com.facade;

import javax.ejb.Local;

import com.model.User;

/**
 * @author Lars Schuetze
 */

@Local
public interface UserFacade {
	
	public abstract User findUserByEmail(String email);
}

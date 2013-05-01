package com.dao;

import com.model.User;

/**
 * @author Lars Schuetze
 */

public interface UserDAO extends GenericDAO<User> {
	public abstract User findUserByName(String email);
}

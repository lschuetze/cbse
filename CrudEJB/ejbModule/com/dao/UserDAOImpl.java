package com.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import com.model.User;

/**
 * @author Lars Schuetze
 */

@Stateless
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	public UserDAOImpl() {
		super(User.class);
	}

	public User findUserByName(String email) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", email);

		return super.findOneResult(User.FIND_BY_EMAIL, parameters);
	}
}

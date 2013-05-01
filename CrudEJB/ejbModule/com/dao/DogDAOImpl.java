package com.dao;

import javax.ejb.Stateless;

import com.model.Dog;

/**
 * @author Lars Schuetze
 *
 */

@Stateless
public class DogDAOImpl extends GenericDAOImpl<Dog> implements DogDAO {

	public DogDAOImpl() {
		super(Dog.class);
	}
	
}

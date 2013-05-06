package de.manuel.proto.businesslogic;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.manuel.proto.business.HelloWorld;
import de.manuel.proto.entity.UserEntity;

/**
 * Session Bean implementation class HelloWorldBean Named to HelloWorld, not
 * sure how to use it....
 */
@Stateless(mappedName = "HelloWorld")
public class HelloWorldBean implements HelloWorld {

	private UserEntity testEntity;

	// Entity Manager for persistence Service
	@PersistenceContext
	private EntityManager entityManager;

	// Example method gives back the username
	@Override
	public String sayHello() {
		entityManager.persist(testEntity);
		return testEntity.getUsername();
	}

	// Sets username to a name of a html-page
	public HelloWorldBean() {
		testEntity = new UserEntity();
		testEntity.setUsername("foo");
	}

	// can be used to change something after the constructor
	@PostConstruct
	public void init() {
		// Username would be set to foo2
		// testEntity.setUsername("foo2");
	}

}

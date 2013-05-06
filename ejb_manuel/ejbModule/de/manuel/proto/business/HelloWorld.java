package de.manuel.proto.business;

import javax.ejb.Remote;

/**
 * Interface for HelloWorld-Service
 * @author Manuel
 *
 */
@Remote
public interface HelloWorld {
	
	public String sayHello();

}

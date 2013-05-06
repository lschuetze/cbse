package mbean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import de.manuel.proto.business.HelloWorld;

/**
 * Managed Bean which is usable in server facets.
 * 
 * @author Manuel
 * 
 */
@ManagedBean
public class UserBean {

	@EJB
	private HelloWorld helloService;

	// A local property variable. Usable through the getters and setters.
	private String testString = "hallo";

	public UserBean() {
		testString = "123";
	}

	/**
	 * Uses an EJB to say "foo" and redirect so to the "foo"-page
	 */
	public String sayHello() {
		String result = helloService.sayHello();
		return result;
	}

	public String getTest() {
		return testString;
	}

	public void setTest(String test) {
		this.testString = test;
	}
}
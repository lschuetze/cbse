package st.cbse.umeet.datatype;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: UserDetails
 * 
 */
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String email;
	private String name;
	private String password;

	public User() {
		super();
	}
	
	public static User create() {
		return new User();
	}

	public String getEmail() {
		return this.email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return this.password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

}

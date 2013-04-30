package de.prototype.lars;

/**
 * @author Lars Schuetze
 * 
 */
public class LoginBean {

	private String name;
	private String password;

	public LoginBean() {
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
	
	public String login() { return "true"; }

	public String login(final String name, final String password) {
		return (this.name.equals(name) && this.password.equals(password)) ? "true"
				: "false";
	}
}

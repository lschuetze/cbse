package st.cbse.umeet.dto;

public class UserDetails {

	private String email;
	private String name;
	private String password;
	
	public static UserDetails create(){
		return new UserDetails();
	}

	public String getEmail() {
		return email;
	}

	public UserDetails setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getName() {
		return name;
	}

	public UserDetails setName(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserDetails setPassword(String password) {
		this.password = password;
		return this;
	}
}

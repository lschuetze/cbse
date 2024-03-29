package st.cbse.umeet.dto;

import st.cbse.umeet.datatype.User;

public class UserDetails {

	private String email;
	private String name;
	private String password;

	private UserDetails() {
		super();
	}

	public static UserDetails create() {
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
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("Moep!");
		if(obj instanceof UserDetails){
			return ((UserDetails)obj).getEmail().equals(email);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return email.hashCode();
	}
}

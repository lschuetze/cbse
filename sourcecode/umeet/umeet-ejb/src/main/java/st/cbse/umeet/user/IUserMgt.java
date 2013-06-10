package st.cbse.umeet.user;

import java.util.List;

import st.cbse.umeet.datatype.User;
import st.cbse.umeet.dto.UserDetails;

public interface IUserMgt {
	
	public User login(String email, String password);
	
	public boolean registerUser(UserDetails userDetails);
	
	/**
	 * Only for internal use to get access to dataype <code>User</code>
	 * @param userDetails the details object of an user
	 * @return
	 * An {@link User} object
	 */
	public User parseDetails(UserDetails userDetails);

	public List<User> parseDetails(List<UserDetails> participants);

	public UserDetails parseUser(User creator);

	public List<UserDetails> parseUser(List<User> userList);

}

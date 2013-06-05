package st.cbse.umeet.user;

import st.cbse.umeet.datatype.User;
import st.cbse.umeet.dto.UserDetails;

public interface IUserMgt {
	
	/**
	 * Only for internal use to get access to dataype <code>User</code>
	 * @param userDetails the details object of an user
	 * @return
	 * An {@link User} object
	 */
	public User parseDetails(UserDetails userDetails);

}

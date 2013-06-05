package st.cbse.umeet.user;

import javax.ejb.Local;

import st.cbse.umeet.datatype.User;
import st.cbse.umeet.dto.UserDetails;

public interface IUserMgt {
	
	/**
	 * Only for internal use to get access to dataype <code>User</code>
	 * @param userDetails
	 * @return
	 */
	public User parseDetails(UserDetails userDetails);

}

package st.cbse.umeet.user;

import javax.ejb.Stateless;

import st.cbse.umeet.datatype.User;
import st.cbse.umeet.dto.UserDetails;

@Stateless
public class UserMgr implements IUserMgt {

	@Override
	public User parseDetails(UserDetails userDetails) {
		User user = new User().setEmail(userDetails.getEmail())
				.setName(userDetails.getName())
				.setPassword(userDetails.getPassword());
		return user;
	}

}

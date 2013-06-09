package st.cbse.umeet.user;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;

import st.cbse.umeet.datatype.User;
import st.cbse.umeet.dto.UserDetails;

@Stateless
public class UserMgr implements IUserMgt {

	@Override
	public User parseDetails(UserDetails userDetails) {
		if(userDetails == null) {
			// TODO @Manuel give something other back
			return null;
		}
		User user = new User().setEmail(userDetails.getEmail())
				.setName(userDetails.getName())
				.setPassword(userDetails.getPassword());
		return user;
	}

	@Override
	public List<User> parseDetails(List<UserDetails> participants) {
		List<User> userList = new LinkedList<User>();
		if(participants == null){
			return userList;
		}
		for(UserDetails participant : participants) {
			userList.add(parseDetails(participant));
		}
		return userList;
	}

	@Override
	public UserDetails parseUser(User user) {
		if(user == null) {
			return null;
		}
		return new UserDetails().setEmail(user.getEmail()).setName(user.getName());
	}
	
	@Override
	public List<UserDetails> parseUser(List<User> userList) {
		List<UserDetails> userDetailsList = new LinkedList<UserDetails>();
		if(userList == null) {
			return userDetailsList;
		}
		for(User user : userList) {
			userDetailsList.add(parseUser(user));
		}
		return userDetailsList;
	}

}

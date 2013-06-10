package st.cbse.umeet.user;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import st.cbse.umeet.datatype.User;
import st.cbse.umeet.dto.UserDetails;

@Stateless
public class UserMgr implements IUserMgt {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User login(String email, String password) {
		// search for user in database
		TypedQuery<User> query = em
				.createQuery(
						"SELECT u FROM User u WHERE (u.email=:param_email)",
						User.class);
		query.setParameter("param_email", email);
		List<User> list = query.getResultList();
		// check user data
		if (list.isEmpty()) {
			// user does not exist
			// TODO: throw exception instead of returning null?
			return null;
		}
		User user = list.get(0);
		if (user.getPassword().equals(password)) {
			// user exists, but password does not match
			// TODO: throw exception instead of returning null?
			return null;
		}
		// user exists and password matches. all clear!
		return user;
	}

	@Override
	public boolean registerUser(UserDetails userDetails) {
		// search for user with specified email in database
		TypedQuery<User> query = em
				.createQuery(
						"SELECT u FROM User u WHERE (u.email=:param_email)",
						User.class);
		query.setParameter("param_email", userDetails.getEmail());
		List<User> list = query.getResultList();
		// check query answer
		if (!list.isEmpty()) {
			// a user with that email already exists
			return false;
		}
		// user does not exist yet. create it!
		em.persist(parseDetails(userDetails));
		return true;
	}

	@Override
	public User parseDetails(UserDetails userDetails) {
		if (userDetails == null) {
			// TODO @Manuel give something other back
			// remark(stefan): should this throw a NullPointerException?
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
		if (participants == null) {
			return userList;
		}
		for (UserDetails participant : participants) {
			userList.add(parseDetails(participant));
		}
		return userList;
	}

	@Override
	public UserDetails parseUser(User user) {
		if (user == null) {
			return null;
		}
		return UserDetails.create().setEmail(user.getEmail())
				.setName(user.getName());
	}

	@Override
	public List<UserDetails> parseUser(List<User> userList) {
		List<UserDetails> userDetailsList = new LinkedList<UserDetails>();
		if (userList == null) {
			return userDetailsList;
		}
		for (User user : userList) {
			userDetailsList.add(parseUser(user));
		}
		return userDetailsList;
	}

}

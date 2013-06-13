package st.cbse.umeet.system;

import java.util.List;

import javax.ejb.Local;

import st.cbse.umeet.dto.UserDetails;

@Local
public interface IGetAllUsers {
	
	public List<UserDetails> getAllUsers();

}

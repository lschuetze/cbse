package st.cbse.umeet.system;

import javax.ejb.Local;

@Local
public interface IRegisterUser {

	public boolean registerUser(String email, String name, String password);

}

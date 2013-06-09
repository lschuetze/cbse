package st.cbse.umeet.system;

import javax.ejb.Local;

@Local
public interface ILogin {
	
	public boolean login(String email, String password); 

}

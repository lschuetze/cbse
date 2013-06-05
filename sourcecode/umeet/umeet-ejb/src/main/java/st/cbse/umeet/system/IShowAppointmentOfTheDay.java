package st.cbse.umeet.system;

import java.util.List;

import javax.ejb.Local;

import st.cbse.umeet.dto.AppointmentDetails;

@Local
public interface IShowAppointmentOfTheDay {
	
	public List<AppointmentDetails> getAppointments(String email, Long date);

}

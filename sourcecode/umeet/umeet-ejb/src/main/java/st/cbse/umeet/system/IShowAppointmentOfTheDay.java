package st.cbse.umeet.system;

import javax.ejb.Local;

import st.cbse.umeet.dto.AppointmentDetails;

@Local
public interface IShowAppointmentOfTheDay {
	
	public AppointmentDetails getAppointments();

}

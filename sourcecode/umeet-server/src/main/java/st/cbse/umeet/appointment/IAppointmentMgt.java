package st.cbse.umeet.appointment;

import java.util.List;

import st.cbse.umeet.dto.AppointmentDetails;
import st.cbse.umeet.dto.UserDetails;

public interface IAppointmentMgt {
	
	public AppointmentDetails createAppointment(AppointmentDetails appDetails);

	public List<AppointmentDetails> showAppointmentsOfDay(UserDetails user, Long date);

}

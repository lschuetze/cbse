package st.cbse.umeat.appointment;

import java.util.List;

import st.cbse.umeat.dto.AppointmentDetails;
import st.cbse.umeat.dto.UserDetails;

public interface IAppointmentMgt {
	
	public AppointmentDetails createAppointment(AppointmentDetails appDetails);

	public List<AppointmentDetails> showAppointmentsOfDay(UserDetails user, Long date);

}

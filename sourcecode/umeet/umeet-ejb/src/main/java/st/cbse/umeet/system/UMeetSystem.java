package st.cbse.umeet.system;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import st.cbse.umeet.appointment.IAppointmentMgt;
import st.cbse.umeet.dto.AppointmentDetails;
import st.cbse.umeet.dto.UserDetails;

@Stateless
public class UMeetSystem implements IShowAppointmentOfTheDay {
	
	@EJB
	IAppointmentMgt appMgr;

	@Override
	public List<AppointmentDetails> getAppointments(String email, Long date) {
		UserDetails user = new UserDetails().setEmail(email);
		return appMgr.showAppointmentsOfDay(user, date);
	}

}

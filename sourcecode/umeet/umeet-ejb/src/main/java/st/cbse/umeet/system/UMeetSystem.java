package st.cbse.umeet.system;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import st.cbse.umeet.appointment.IAppointmentMgt;
import st.cbse.umeet.dto.AppointmentDetails;
import st.cbse.umeet.dto.UserDetails;

@Stateless
public class UMeetSystem implements IShowAppointmentOfTheDay,
		ICreateAppointment {

	@EJB
	IAppointmentMgt appMgr;

	@Override
	public List<AppointmentDetails> getAppointments(String email, Long date) {
		UserDetails user = new UserDetails().setEmail(email);
		return appMgr.showAppointmentsOfDay(user, date);
	}

	@Override
	public Boolean createAppointment(String creatorEmail, Long startDate,
			Long endDate, String title, String status, String notes,
			Boolean personal, List<String> participantsEmail) throws Exception {
		List<UserDetails> participantList = new LinkedList<UserDetails>();
		for (String m : participantsEmail) {
			participantList.add(new UserDetails().setEmail(m));
		}
		AppointmentDetails appDetails = new AppointmentDetails()
				.setCreator(new UserDetails().setEmail(creatorEmail))
				.setStartDate(startDate).setEndDate(endDate).setTitle(title)
				.setStatus(status).setNotes(notes).setPersonal(personal)
				.setParticipants(participantList);
		return appMgr.createAppointment(appDetails);
	}

	@Override
	public List<AppointmentDetails> getConflicts(String creatorEmail,
			Long startDate, Long endDate, String status, Boolean personal,
			List<String> participantsEmail) throws Exception {
		List<UserDetails> participantList = new LinkedList<UserDetails>();
		for (String m : participantsEmail) {
			participantList.add(new UserDetails().setEmail(m));
		}
		AppointmentDetails appDetails = new AppointmentDetails()
				.setCreator(new UserDetails().setEmail(creatorEmail))
				.setStartDate(startDate).setEndDate(endDate).setStatus(status)
				.setPersonal(personal).setParticipants(participantList);
		return appMgr.getConflicts(appDetails);
	}

}

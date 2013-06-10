package st.cbse.umeet.system;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import st.cbse.umeet.appointment.IAppointmentMgt;
import st.cbse.umeet.datatype.User;
import st.cbse.umeet.dto.AppointmentDetails;
import st.cbse.umeet.dto.UserDetails;
import st.cbse.umeet.user.IUserMgt;

@Stateless
public class UMeetSystem implements IShowAppointmentOfTheDay,
		ICreateAppointment, IRegisterUser, ILogin {

	@EJB
	IAppointmentMgt appMgr;

	@EJB
	IUserMgt userMgr;

	@Override
	public List<AppointmentDetails> getAppointments(String email, Long date) {

		UserDetails user = UserDetails.create().setEmail(email);
		return appMgr.showAppointmentsOfDay(user, date);
	}

	@Override
	public Boolean createAppointment(String creatorEmail, Long startDate,
			Long endDate, String title, String status, String notes,
			Boolean personal, List<String> participantsEmail) throws Exception {

		List<UserDetails> participantList = new LinkedList<>();
		for (String m : participantsEmail) {
			participantList.add(UserDetails.create().setEmail(m));
		}

		AppointmentDetails appDetails = AppointmentDetails.create()
				.setCreator(UserDetails.create().setEmail(creatorEmail))
				.setStartDate(startDate).setEndDate(endDate).setTitle(title)
				.setStatus(status).setNotes(notes).setPersonal(personal)
				.setParticipants(participantList);

		return appMgr.createAppointment(appDetails);
	}

	@Override
	public List<AppointmentDetails> getConflicts(String creatorEmail,
			Long startDate, Long endDate, String status, Boolean personal,
			List<String> participantsEmail) throws Exception {

		List<UserDetails> participantList = new LinkedList<>();
		for (String m : participantsEmail) {
			participantList.add(UserDetails.create().setEmail(m));
		}

		AppointmentDetails appDetails = AppointmentDetails.create()
				.setCreator(UserDetails.create().setEmail(creatorEmail))
				.setStartDate(startDate).setEndDate(endDate).setStatus(status)
				.setPersonal(personal).setParticipants(participantList);

		return appMgr.getConflicts(appDetails);
	}

	@Override
	public boolean registerUser(String email, String name, String password) {

		UserDetails details = UserDetails.create().setEmail(email)
				.setName(name).setPassword(password);
		return userMgr.registerUser(details);
	}

	@Override
	public boolean login(String email, String password) {

		User user = userMgr.login(email, password);
		if (user == null)
			return false;
		// TODO: do something with User instance?
		return true;
	}

}

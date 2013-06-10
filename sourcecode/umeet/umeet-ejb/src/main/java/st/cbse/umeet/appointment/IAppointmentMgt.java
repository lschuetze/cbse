package st.cbse.umeet.appointment;

import java.util.List;

import st.cbse.umeet.dto.AppointmentDetails;
import st.cbse.umeet.dto.UserDetails;

public interface IAppointmentMgt {

	/**
	 * Search all appointments for a given date and user.
	 * 
	 * @param user
	 *            the user as participant or creator of the appointment.
	 * @param date
	 *            the long representation of the date of the appointments. Has
	 *            to be at 00:00.
	 * @return A list of {@link AppointmentDetails}
	 */
	public List<AppointmentDetails> showAppointmentsOfDay(UserDetails user,
			Long date);

	/**
	 * Checks for conflict during given appointment. <br>
	 * What appointment details must have, see in
	 * {@link AppointmentMgr#isAppointmentDetailsCorrect(AppointmentDetails)}
	 * 
	 * @param appDetails
	 * @return
	 * @throws Exception
	 */
	public List<AppointmentDetails> getConflicts(AppointmentDetails appDetails)
			throws Exception;

	/**
	 * Creates an appointment. <br>
	 * What appointment details must have, see in
	 * {@link AppointmentMgr#isAppointmentDetailsCorrect(AppointmentDetails)}
	 * 
	 * @param appDetails
	 * @return
	 * @throws Exception
	 */
	public Boolean createAppointment(AppointmentDetails appDetails)
			throws Exception;

}

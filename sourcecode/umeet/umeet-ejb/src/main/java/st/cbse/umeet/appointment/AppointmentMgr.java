package st.cbse.umeet.appointment;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import st.cbse.umeet.datatype.Appointment;
import st.cbse.umeet.datatype.User;
import st.cbse.umeet.dto.AppointmentDetails;
import st.cbse.umeet.dto.UserDetails;
import st.cbse.umeet.user.IUserMgt;

@Stateless
public class AppointmentMgr implements IAppointmentMgt {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private IUserMgt userMgr;

	/**
	 * {@inheritDoc}
	 */
	// TODO @Manuel test return value detailed
	@Override
	public List<AppointmentDetails> showAppointmentsOfDay(
			UserDetails userDetails, Long date) {
		User user = userMgr.parseDetails(userDetails);
		// Calculate milliseconds for one day
		Long oneDay = Long.valueOf((24 * 60 * 60 * 1000));
		// HACK Substract 1000ms puffer for the really exact calendar date
		// just one second before next day
		oneDay -= 1000;
		Long fDate = date + oneDay;
		/*
		 * date has to be the Long representation of the day at 00:00. The
		 * startDate or endDate has to be between date and date+24h (startDate
		 * has to be after date and before date+24h) or (endDate has to be after
		 * date and before date+24h)
		 */
		TypedQuery<Appointment> query = em
				.createQuery(
						"select a from Appointment a left outer join a.participants par where (a.creator=:user or par=:user) and ((a.startDate>=:date and a.startDate<:fDay)"
								+ "or (a.endDate>=:date and a.endDate<:fDay)) GROUP BY a.id",
						Appointment.class);
		query.setParameter("date", date).setParameter("fDay", fDate)
				.setParameter("user", user);
		List<Appointment> results = query.getResultList();
		List<AppointmentDetails> appDetailsList = new LinkedList<AppointmentDetails>();
		for (Appointment app : results) {
			appDetailsList.add(parseAppointment(app));
		}
		return appDetailsList;
	}

	/**
	 * {@inheritDoc}
	 */
	// TODO @Manuel test return values detailed
	@Override
	public List<AppointmentDetails> getConflicts(AppointmentDetails appDetails)
			throws Exception {
		List<AppointmentDetails> appDetailsList = new LinkedList<AppointmentDetails>();
		if (!isAppointmentDetailsCorrect(appDetails)) {
			throw new Exception("Appointment Details incorrect");
		} else if (appDetails.getStatus() == AppointmentStatus.FREE.toString()) {
			// An appointment with free status doesn't trigger a conflict
			return appDetailsList;
		}
		Appointment appointment = parseDetails(appDetails);
		List<User> userList = new LinkedList<User>();
		userList.add(appointment.getCreator());
		userList.addAll(appointment.getParticipants());
		/*
		 * Conflict situations:
		 * 
		 * - Another appointment of a participating or creating user is during
		 * the given time <-- implemented in the query
		 * 
		 * - None of the both appointments is of the type "Free" <-- implemented
		 * in the result-loop
		 * 
		 * - Remove private appointments which are not from the creator of the
		 * new appointment <-- implemented in the result-loop
		 */
		TypedQuery<Appointment> query = em
				.createQuery(
						"select a from Appointment a left outer join a.participants par where (a.creator IN (:userList) or par IN (:userList)) and ((a.startDate>=:date and a.startDate<:fDay)"
								+ "or (a.endDate>=:date and a.endDate<:fDay)) GROUP BY a.id",
						Appointment.class);
		query.setParameter("date", appointment.getStartDate())
				.setParameter("fDay", appointment.getEndDate())
				.setParameter("userList", userList);
		List<Appointment> results = query.getResultList();
		for (Appointment app : results) {
			// "Free" status of the appointment is ignored and doesn't cause a
			// conflict
			if (app.getStatus() != AppointmentStatus.FREE.toString()) {
				// Remove private appointments details which are not from the
				// creator of the new appointment
				if (app.getPersonal() == true
						&& app.getCreator().getEmail() != appDetails
								.getCreator().getEmail()) {
					// Remove interesting parts
					app.setTitle("").setNotes("").setStatus("");
				}
				appDetailsList.add(parseAppointment(app));
			}
		}
		return appDetailsList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean createAppointment(AppointmentDetails appDetails)
			throws Exception {
		// Create the appointment if no conflicts exist
		if (getConflicts(appDetails).size() == 0) {
			Appointment app = parseDetails(appDetails);
			em.persist(app);
			return true;
		}
		// any error returns a false
		return false;
	}

	/**
	 * Correct appointment details must have:<br>
	 * - creator<br>
	 * - startDate<br>
	 * - endDate<br>
	 * - personal<br>
	 * - status
	 * 
	 * @param appDetails
	 *            The appointment details to check
	 * @return <code>true</code> when appointment details are correct.
	 *         <code>false</code> otherwise.
	 */
	protected Boolean isAppointmentDetailsCorrect(AppointmentDetails appDetails) {
		if (appDetails.getCreator() == null
				|| appDetails.getStartDate() == null
				|| appDetails.getEndDate() == null
				|| appDetails.getPersonal() == null
				|| AppointmentStatus.fromString(appDetails.getStatus()) == null) {
			return false;
		}
		return true;
	}

	/**
	 * Parses a details object to a datatype object.
	 * 
	 * @param appDetails
	 *            the detail object of an appointment
	 * @return An {@link Appointment} object
	 */
	private Appointment parseDetails(AppointmentDetails appDetails) {
		Appointment app = new Appointment()
				.setStartDate(appDetails.getStartDate())
				.setEndDate(appDetails.getEndDate())
				.setId(appDetails.getId())
				.setTitle(appDetails.getTitle())
				.setNotes(appDetails.getNotes())
				.setPersonal(appDetails.getPersonal())
				.setStatus(appDetails.getStatus())
				.setCreator(userMgr.parseDetails(appDetails.getCreator()))
				.setParticipants(
						userMgr.parseDetails(appDetails.getParticipants()));
		return app;
	}

	/**
	 * Parses a datatype object to a details object.
	 * 
	 * @param app
	 *            the appointment object.
	 * @return An {@link AppointmentDetails} object
	 */
	private AppointmentDetails parseAppointment(Appointment app) {
		AppointmentDetails appDetails = AppointmentDetails.create()
				.setStartDate(app.getStartDate()).setEndDate(app.getEndDate())
				.setId(app.getId()).setTitle(app.getTitle())
				.setNotes(app.getNotes()).setPersonal(app.getPersonal())
				.setStatus(app.getStatus())
				.setCreator(userMgr.parseUser(app.getCreator()))
				.setParticipants(userMgr.parseUser(app.getParticipants()));
		return appDetails;
	}

}

enum AppointmentStatus {
	BLOCKED("Blocked"), FREE("Free"), POTENTIALLY_BLOCKED("Potentially blocked"), AWAY(
			"Away");

	private final String status;

	private AppointmentStatus(final String status) {
		this.status = status;
	}

	public static AppointmentStatus fromString(String status) {
		if (status != null) {
			for (AppointmentStatus a : AppointmentStatus.values()) {
				if (status.equalsIgnoreCase(a.status)) {
					return a;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return status;
	}
}

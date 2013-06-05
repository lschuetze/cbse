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

	@Override
	public AppointmentDetails createAppointment(AppointmentDetails appDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppointmentDetails> showAppointmentsOfDay(UserDetails userDetails,
			Long date) {
		User user = userMgr.parseDetails(userDetails);
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
		TypedQuery<Appointment> query = em.createQuery(
				"select a from Appointment a left outer join a.participants par where (a.creator=:user or par=:user) and ((a.startDate>=:date and a.startDate<:fDay)"
						+ "or (a.endDate>=:date and a.endDate<:fDay)) GROUP BY a.id",
				Appointment.class);
		query.setParameter("date", date).setParameter("fDay", fDate).setParameter("user", user);
		List<Appointment> results = query.getResultList();
		List<AppointmentDetails> appDetailsList = new LinkedList<AppointmentDetails>();
		for (Appointment app : results) {
			appDetailsList.add(parseAppointment(app));
		}
		return appDetailsList;
	}

	/**
	 * Parses a details object to a datatype object.
	 * @param appDetails the detail object of an appointment
	 * @return
	 * An {@link Appointment} object
	 */
	private Appointment parseDetails(AppointmentDetails appDetails) {
		Appointment app = new Appointment()
				.setStartDate(appDetails.getStartDate())
				.setEndDate(appDetails.getEndDate()).setId(appDetails.getId())
				.setTitle(appDetails.getTitle())
				.setNotes(appDetails.getNotes())
				.setPersonal(appDetails.getPersonal())
				.setStatus(appDetails.getStatus());
		// TODO @Manuel: Think about good implementation
		// app.setCreator(appDetails.getCreator());
		// TODO @Manuel: Think about good implementation
		// app.setParticipants(appDetails.getParticipants());
		return app;
	}

	/**
	 * Parses a datatype object to a details object.
	 * @param app the appointment object.
	 * @return
	 * An {@link AppointmentDetails} object
	 */
	private AppointmentDetails parseAppointment(Appointment app) {
		AppointmentDetails appDetails = new AppointmentDetails()
				.setStartDate(app.getStartDate()).setEndDate(app.getEndDate())
				.setId(app.getId()).setTitle(app.getTitle())
				.setNotes(app.getNotes()).setPersonal(app.getPersonal())
				.setStatus(app.getStatus());
		// TODO @Manuel: Think about good implementation
		// app.setCreator(appDetails.getCreator());
		// TODO @Manuel: Think about good implementation
		// app.setParticipants(appDetails.getParticipants());
		return appDetails;
	}

}

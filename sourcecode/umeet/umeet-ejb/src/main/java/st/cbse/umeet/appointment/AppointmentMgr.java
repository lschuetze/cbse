package st.cbse.umeet.appointment;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import st.cbse.umeet.datatype.Appointment;
import st.cbse.umeet.dto.AppointmentDetails;
import st.cbse.umeet.dto.UserDetails;

@Stateless
public class AppointmentMgr implements IAppointmentMgt {

	@PersistenceContext
	EntityManager em;

	@Override
	public AppointmentDetails createAppointment(AppointmentDetails appDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppointmentDetails> showAppointmentsOfDay(UserDetails user,
			Long date) {
		Long oneDay = Long.valueOf((24 * 60 * 60 * 1000));
		// HACK Substract 1000ms puffer for the really exact calendar date
		// just one s before next day
		oneDay -= 1000;
		Long fDate = date + oneDay;
		/*
		 * date has to be the Long representation of the day at 00:00. The
		 * startDate or endDate has to be between date and date+24h (startDate
		 * has to be after date and before date+24h) or (endDate has to be after
		 * date and before date+24h)
		 */
		TypedQuery<Appointment> query = em.createQuery(
				"select a from Appointment a where (a.startDate>=:date and a.startDate<:fDay)"
						+ "or (a.endDate>=:date and a.endDate<:fDay)",
				Appointment.class);
		query.setParameter("date", date).setParameter("fDay", fDate);
		List<Appointment> results = query.getResultList();
		List<AppointmentDetails> appDetailsList = new LinkedList<>();
		for (Appointment app : results) {
			appDetailsList.add(parseAppointment(app));
		}
		return appDetailsList;
	}

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

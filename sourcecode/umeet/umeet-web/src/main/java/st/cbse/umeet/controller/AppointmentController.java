package st.cbse.umeet.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import st.cbse.umeet.dto.AppointmentDetails;
import st.cbse.umeet.system.ICreateAppointment;
import st.cbse.umeet.system.IShowAppointmentOfTheDay;

@ManagedBean
@SessionScoped
public class AppointmentController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	FacesContext context;

	@Inject
	private IShowAppointmentOfTheDay appointmentOfTheDay;

	@Inject
	private ICreateAppointment appointmentCreator;

	private AppointmentDetails[] appointmentsOfTheDay;

	public AppointmentDetails[] getAppointmentsOfTheDay() {
		String email = (String) context.getExternalContext().getSessionMap()
				.get("user");
		AppointmentDetails[] result = appointmentOfTheDay.getAppointments(
				email, new Date().getTime()).toArray(new AppointmentDetails[0]);
		
		System.out.println("======> " + result.length);
		
		return result;
	}
}

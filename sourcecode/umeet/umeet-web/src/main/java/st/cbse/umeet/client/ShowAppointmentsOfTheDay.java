package st.cbse.umeet.client;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import st.cbse.umeet.system.IShowAppointmentOfTheDay;

@ManagedBean
public class ShowAppointmentsOfTheDay {
	
	@Inject
	private IShowAppointmentOfTheDay ej;
	
	public String foo="test";
	

}
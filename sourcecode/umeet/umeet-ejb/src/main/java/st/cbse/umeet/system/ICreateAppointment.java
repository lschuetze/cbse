package st.cbse.umeet.system;

import java.util.List;

import st.cbse.umeet.dto.AppointmentDetails;

public interface ICreateAppointment {
	
	public Boolean createAppointment();
	
	public List<AppointmentDetails> getConflicts();

}

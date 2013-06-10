package st.cbse.umeet.system;

import java.util.List;

import st.cbse.umeet.dto.AppointmentDetails;

public interface ICreateAppointment {

	/**
	 * TODO @Manuel only one function for testing for valid and complete input
	 * data.... perhaps get it out of AppointmentMgr.
	 * 
	 * @return
	 * @param creatorEmail
	 * @param startDate
	 * @param endDate
	 * @param title
	 * @param status
	 * @param notes
	 * @param personal
	 * @param participantsEmail
	 * @return
	 * @throws Exception
	 */
	public Boolean createAppointment(String creatorEmail, Long startDate,
			Long endDate, String title, String status, String notes,
			Boolean personal, List<String> participantsEmail) throws Exception;

	public List<AppointmentDetails> getConflicts(String creatorEmail,
			Long startDate, Long endDate, String status, Boolean personal,
			List<String> participantsEmail) throws Exception;

}

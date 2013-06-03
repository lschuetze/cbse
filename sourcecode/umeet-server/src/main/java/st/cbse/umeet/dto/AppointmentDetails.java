package st.cbse.umeet.dto;

import java.util.List;

public class AppointmentDetails {
	private UserDetails creator;
	private Long endDate;
	private String notes;
	private List<UserDetails> participants;
	private Boolean personal;
	private Long startDate;
	private String status;
	private String title;

	public UserDetails getCreator() {
		return creator;
	}

	public void setCreator(UserDetails creator) {
		this.creator = creator;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<UserDetails> getParticipants() {
		return participants;
	}

	public void setParticipants(List<UserDetails> participants) {
		this.participants = participants;
	}

	public Boolean getPersonal() {
		return personal;
	}

	public void setPersonal(Boolean personal) {
		this.personal = personal;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

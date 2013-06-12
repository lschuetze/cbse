package st.cbse.umeet.dto;

import java.util.Date;
import java.util.List;

public class AppointmentDetails {
	private Long id;
	private UserDetails creator;
	private Long endDate;
	private String notes;
	private List<UserDetails> participants;
	private Boolean personal;
	private Long startDate;
	private String status;
	private String title;

	public static AppointmentDetails create() {
		return new AppointmentDetails();
	}
	
	private AppointmentDetails() {
		super();
	}

	public UserDetails getCreator() {
		return creator;
	}

	public AppointmentDetails setCreator(UserDetails creator) {
		this.creator = creator;
		return this;
	}

	public Long getEndDate() {
		return endDate;
	}
	
	public Date getEndDateAsDate() {
		return new Date(endDate);
	}
	
	public Date getStartDateAsDate() {
		return new Date(endDate);
	}

	public AppointmentDetails setEndDate(Long endDate) {
		this.endDate = endDate;
		return this;
	}

	public String getNotes() {
		return notes;
	}

	public AppointmentDetails setNotes(String notes) {
		this.notes = notes;
		return this;
	}

	public List<UserDetails> getParticipants() {
		return participants;
	}

	public AppointmentDetails setParticipants(List<UserDetails> participants) {
		this.participants = participants;
		return this;
	}

	public Boolean getPersonal() {
		return personal;
	}

	public AppointmentDetails setPersonal(Boolean personal) {
		this.personal = personal;
		return this;
	}

	public Long getStartDate() {
		return startDate;
	}

	public AppointmentDetails setStartDate(Long startDate) {
		this.startDate = startDate;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public AppointmentDetails setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public AppointmentDetails setTitle(String title) {
		this.title = title;
		return this;
	}

	public Long getId() {
		return id;
	}

	public AppointmentDetails setId(Long id) {
		this.id = id;
		return this;
	}

}

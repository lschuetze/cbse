package st.cbse.umeet.dto;

import java.util.Date;
import java.util.List;

public class AppointmentDetails {
	private long id;
	private UserDetails creator;
	private long endDate = 0;
	private String notes;
	private List<UserDetails> participants;
	private boolean personal = false;
	private long startDate = 0;
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

	public long getEndDate() {
		return endDate;
	}

	public Date getEndDateAsDate() {
		return new Date(endDate);
	}

	public Date getStartDateAsDate() {
		return new Date(startDate);
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

	public boolean getPersonal() {
		return personal;
	}

	public AppointmentDetails setPersonal(boolean personal) {
		this.personal = personal;
		return this;
	}

	public long getStartDate() {
		return startDate;
	}

	public AppointmentDetails setStartDate(long startDate) {
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

	public long getId() {
		return id;
	}

	public AppointmentDetails setId(long id) {
		this.id = id;
		return this;
	}

}

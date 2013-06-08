package st.cbse.umeet.datatype;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: AppointmentDetails
 * 
 */
@Entity
public class Appointment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private User creator;
	private Long endDate;

	@Id
	@GeneratedValue
	private Long id;
	private String notes;
	@ManyToMany
	private List<User> participants;
	private Boolean personal;
	private Long startDate;
	private String status;
	private String title;

	public Appointment() {
		super();
	}

	public Appointment(String title, Long startDate, Long endDate,
			User creator, String status, Boolean personal) {
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creator = creator;
		this.status = status;
		this.personal = personal;
	}
	
	public static Appointment create() {
		return new Appointment();
	}

	public User getCreator() {
		return this.creator;
	}

	public Long getEndDate() {
		return this.endDate;
	}

	public Long getId() {
		return id;
	}

	public String getNotes() {
		return this.notes;
	}

	public List<User> getParticipants() {
		return this.participants;
	}

	public Boolean getPersonal() {
		return this.personal;
	}

	public Long getStartDate() {
		return this.startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public String getTitle() {
		return this.title;
	}

	public Appointment setCreator(User creator) {
		this.creator = creator;
		return this;
	}

	public Appointment setEndDate(Long endDate) {
		this.endDate = endDate;
		return this;
	}

	public Appointment setId(Long id) {
		this.id = id;
		return this;
	}

	public Appointment setNotes(String notes) {
		this.notes = notes;
		return this;
	}

	public Appointment setParticipants(List<User> participants) {
		this.participants = participants;
		return this;
	}

	public Appointment setPersonal(Boolean personal) {
		this.personal = personal;
		return this;
	}

	public Appointment setStartDate(Long startDate) {
		this.startDate = startDate;
		return this;
	}

	public Appointment setStatus(String status) {
		this.status = status;
		return this;
	}

	public Appointment setTitle(String title) {
		this.title = title;
		return this;
	}

}

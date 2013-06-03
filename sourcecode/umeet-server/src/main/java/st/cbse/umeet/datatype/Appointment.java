package st.cbse.umeet.datatype;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
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

	public void setCreator(User creator) {
		this.creator = creator;
	}   
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}   
	public void setPersonal(Boolean personal) {
		this.personal = personal;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}   
	public void setStatus(String status) {
		this.status = status;
	}

	public void setTitle(String title) {
		this.title = title;
	}
   
}

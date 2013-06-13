package st.cbse.umeet.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import st.cbse.umeet.dto.AppointmentDetails;
import st.cbse.umeet.dto.UserDetails;
import st.cbse.umeet.system.ICreateAppointment;
import st.cbse.umeet.system.IGetAllUsers;
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
	
	@Inject
	private IGetAllUsers userProvider;

	private AppointmentDetails[] appointmentsOfTheDay;
	
	//private UserDetails[] invitableUsers;

	private Date startDate, endDate;
	private String title, status, notes;
	private boolean personal;
	private Set<UserDetails> participants;
	private AppointmentDetails[] conflictingAppointments;
	private String chosenParticipant;

	public String getChosenParticipant() {
		return chosenParticipant;
	}

	public void setChosenParticipant(String chosenParticipant) {
		System.out.println("setChosenParticipant" + chosenParticipant);
		this.chosenParticipant = chosenParticipant;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		System.out.println(status);
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean getPersonal() {
		return personal;
	}

	public void setPersonal(boolean personal) {
		this.personal = personal;
	}

	public Set<UserDetails> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<UserDetails> participants) {
		this.participants = participants;
	}
	
	public List<UserDetails> getAllUsers() {
		return userProvider.getAllUsers();
	}

	public AppointmentDetails[] getAppointmentsOfTheDay() {

		String email = (String) context.getExternalContext().getSessionMap()
				.get("user");
		AppointmentDetails[] result = appointmentOfTheDay.getAppointments(
				email, new Date().getTime()).toArray(new AppointmentDetails[0]);

		return result;
	}
	

	public String createAppointment() {
		
		String email = (String) context.getExternalContext().getSessionMap()
				.get("user");
		List<String> partEmail = new LinkedList<String>();
		
		for(UserDetails detail:participants){
			partEmail.add(detail.getEmail());
		}
		

		if (appointmentCreator.createAppointment(email, startDate.getTime()
				, endDate.getTime(), title, status, notes, personal, partEmail)) {		
			return "showAppointments";
		}
		else {
			System.out.println(email+ "\n" + startDate.getTime()+ "\n" + endDate.getTime()+ "\n" + title+ "\n" + status+ "\n" + notes+ "\n" + personal+ "\n" + partEmail);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Stupid program monkey did something wrong here"
					, "Appointment creation unsuccessful");
			context.addMessage(null, m);
			//return "login";
			return "createAppointment";
		}
//		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//				"Username and password did not match"
//				, "Create appointment unsuccessful");
//		facesContext.addMessage(null, m);
		//return "showAppointments";
	}
	
	public String invite(){
		if(participants==null){
			participants = new HashSet<UserDetails>();
		}
		for(UserDetails details : getAllUsers()){
			if(details.getEmail().equals(chosenParticipant)){
				participants.add(details);
			}
		}
		return "createAppointment";
	}
	
	public String disinvite(){
		return "";
	}
}

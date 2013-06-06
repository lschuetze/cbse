package st.cbse.umeet.appointment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import st.cbse.umeet.datatype.Appointment;
import st.cbse.umeet.datatype.User;
import st.cbse.umeet.dto.AppointmentDetails;
import st.cbse.umeet.dto.UserDetails;
import st.cbse.umeet.user.UserMgr;

@RunWith(Arquillian.class)
public class AppointmentMgrTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(AppointmentMgr.class.getPackage())
				.addPackage(Appointment.class.getPackage())
				.addPackage(AppointmentDetails.class.getPackage())
				.addPackage(User.class.getPackage())
				.addPackage(UserMgr.class.getPackage())
				.addAsResource("resources-jboss-managed/persistence-test.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private IAppointmentMgt appMgr;

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private AppointmentDetails appBeforeDate;
	private AppointmentDetails appAfterDate;
	private AppointmentDetails appDuringDate;
	private UserDetails userDetails;
	private Calendar cal = GregorianCalendar.getInstance();
	private static final int YEAR = 2013;
	private static final int MONTH = 5;
	private static final int DAY = 4;
	private static final String USER_EMAIL = "test@cbse.st";

	// Prepare database and insert appointments
	// HINT Test setup includes:
	// - user only as creator
	// - user as creator and participant
	// - user only as participant
	@Test
	@InSequence(0)
	public void insertAppointments() throws Exception {
		// Insert one test user into database
		User user = new User().setName("test").setPassword("test")
				.setEmail(USER_EMAIL);
		utx.begin();
		em.joinTransaction();
		em.persist(user);
		utx.commit();
		// Prepare variables
		userDetails = new UserDetails().setName("test").setPassword("test")
				.setEmail(USER_EMAIL);
		LinkedList<UserDetails> userDetailsList = new LinkedList<UserDetails>();
		userDetailsList.add(userDetails);

		cal.set(YEAR, MONTH, DAY, 0, 0, 0);
		Long date = cal.getTimeInMillis();
		cal.set(YEAR, MONTH, DAY + 1, 0, 0, 0);
		Long dateAfter = cal.getTimeInMillis();
		cal.set(YEAR, MONTH, DAY - 1, 0, 0, 0);
		Long dateBefore = cal.getTimeInMillis();

		// Here is the hint implemented
		appBeforeDate = new AppointmentDetails().setTitle("BeforeDate")
				.setStartDate(dateBefore).setEndDate(dateBefore + 10000)
				.setCreator(userDetails);
		appAfterDate = new AppointmentDetails().setTitle("AfterDate")
				.setStartDate(dateAfter).setEndDate(dateAfter + 10000)
				.setParticipants(userDetailsList);
		appDuringDate = new AppointmentDetails().setTitle("Date")
				.setStartDate(date).setEndDate(date + 10000)
				.setCreator(userDetails).setParticipants(userDetailsList);

		// Insert the appointments
		assertTrue(appMgr.createAppointment(appBeforeDate));
		assertTrue(appMgr.createAppointment(appAfterDate));
		assertTrue(appMgr.createAppointment(appDuringDate));
	}

	@Test
	@InSequence(1)
	public void testDate() throws Exception {
		cal.set(YEAR, MONTH, DAY, 0, 0, 0);
		userDetails = new UserDetails().setEmail(USER_EMAIL);
		assertEquals(
				appMgr.showAppointmentsOfDay(userDetails, cal.getTimeInMillis())
						.size(), 1);
	}

	@Test
	@InSequence(2)
	public void testDateAfter() throws Exception {
		cal.set(YEAR, MONTH, DAY + 1, 0, 0, 0);
		userDetails = new UserDetails().setEmail(USER_EMAIL);
		assertEquals(
				appMgr.showAppointmentsOfDay(userDetails, cal.getTimeInMillis())
						.size(), 1);
	}

	@Test
	@InSequence(3)
	public void testDateAfterEmpty() throws Exception {
		cal.set(YEAR, MONTH, DAY + 2, 0, 0, 0);
		userDetails = new UserDetails().setEmail(USER_EMAIL);
		assertEquals(
				appMgr.showAppointmentsOfDay(userDetails, cal.getTimeInMillis())
						.size(), 0);
	}

	@Test
	@InSequence(4)
	public void testDateBefore() throws Exception {
		cal.set(YEAR, MONTH, DAY - 1, 0, 0, 0);
		userDetails = new UserDetails().setEmail(USER_EMAIL);
		assertEquals(
				appMgr.showAppointmentsOfDay(userDetails, cal.getTimeInMillis())
						.size(), 1);
	}

	@Test
	@InSequence(5)
	public void testDateBeforeEmpty() throws Exception {
		cal.set(YEAR, MONTH, DAY + 2, 0, 0, 0);
		userDetails = new UserDetails().setEmail(USER_EMAIL);
		assertEquals(
				appMgr.showAppointmentsOfDay(userDetails, cal.getTimeInMillis())
						.size(), 0);
	}

	@Test
	@InSequence(6)
	public void testCreateEmptyAppointment() throws Exception {
		assertTrue(appMgr.createAppointment(new AppointmentDetails()));
	}
}

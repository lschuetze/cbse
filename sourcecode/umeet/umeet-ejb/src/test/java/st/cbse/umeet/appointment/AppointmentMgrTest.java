package st.cbse.umeet.appointment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import org.junit.Before;
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
	private Long date;
	private Long dateAfter;
	private Long dateBefore;
	private UserDetails userDetails;
	private UserDetails userDetails2;
	private Calendar cal = GregorianCalendar.getInstance();
	private static final int YEAR = 2013;
	private static final int MONTH = 5;
	private static final int DAY = 4;
	private static final String USER_EMAIL = "test@cbse.st";
	private static final String USER_EMAIL_2 = "test2@cbse.st";

	@Before
	public void beforeClass() throws Exception {
		// Prepare variables
		userDetails = UserDetails.create().setName("test").setPassword("test")
				.setEmail(USER_EMAIL);
		userDetails2 = UserDetails.create().setName("test2").setPassword("test")
				.setEmail(USER_EMAIL_2);
		LinkedList<UserDetails> userDetailsList = new LinkedList<UserDetails>();
		userDetailsList.add(userDetails);

		cal.set(YEAR, MONTH, DAY, 0, 0, 0);
		date = cal.getTimeInMillis();
		cal.set(YEAR, MONTH, DAY + 1, 0, 0, 0);
		dateAfter = cal.getTimeInMillis();
		cal.set(YEAR, MONTH, DAY - 1, 0, 0, 0);
		dateBefore = cal.getTimeInMillis();

		// Here is the hint implemented
		appBeforeDate = AppointmentDetails.create().setTitle("BeforeDate")
				.setStartDate(dateBefore).setEndDate(dateBefore + 10000)
				.setCreator(userDetails)
				.setStatus(AppointmentStatus.AWAY.toString())
				.setPersonal(false);
		appAfterDate = AppointmentDetails.create().setTitle("AfterDate")
				.setStartDate(dateAfter).setEndDate(dateAfter + 10000)
				.setCreator(userDetails2).setParticipants(userDetailsList)
				.setStatus(AppointmentStatus.AWAY.toString())
				.setPersonal(false);
		appDuringDate = AppointmentDetails.create().setTitle("Date")
				.setStartDate(date).setEndDate(date + 10000)
				.setCreator(userDetails).setParticipants(userDetailsList)
				.setStatus(AppointmentStatus.AWAY.toString())
				.setPersonal(false);
	}

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
		User user2 = new User().setName("test").setPassword("test")
				.setEmail(USER_EMAIL_2);
		utx.begin();
		em.joinTransaction();
		em.persist(user);
		em.persist(user2);
		utx.commit();

		// Insert the appointments
		assertTrue(appMgr.createAppointment(appDuringDate));
		assertTrue(appMgr.createAppointment(appBeforeDate));
		assertTrue(appMgr.createAppointment(appAfterDate));
	}

	@Test
	@InSequence(1)
	public void testDate() throws Exception {
		assertEquals(appMgr.showAppointmentsOfDay(userDetails, date).size(), 1);
	}

	@Test
	@InSequence(2)
	public void testDateAfter() throws Exception {
		userDetails = UserDetails.create().setEmail(USER_EMAIL);
		assertEquals(appMgr.showAppointmentsOfDay(userDetails, dateAfter)
				.size(), 1);
	}

	@Test
	@InSequence(3)
	public void testDateAfterEmpty() throws Exception {
		userDetails = UserDetails.create().setEmail(USER_EMAIL);
		cal.setTimeInMillis(dateAfter);
		cal.roll(Calendar.DAY_OF_MONTH, true);
		assertEquals(appMgr.showAppointmentsOfDay(userDetails, cal.getTimeInMillis())
				.size(), 0);
	}

	@Test
	@InSequence(4)
	public void testDateBefore() throws Exception {
		userDetails = UserDetails.create().setEmail(USER_EMAIL);
		assertEquals(appMgr.showAppointmentsOfDay(userDetails, dateBefore)
				.size(), 1);
	}

	@Test
	@InSequence(5)
	public void testDateBeforeEmpty() throws Exception {
		userDetails = UserDetails.create().setEmail(USER_EMAIL);
		cal.setTimeInMillis(dateBefore);
		cal.roll(Calendar.DAY_OF_MONTH, false);
		assertEquals(appMgr.showAppointmentsOfDay(userDetails, cal.getTimeInMillis())
				.size(), 0);
	}

	@Test
	@InSequence(6)
	public void testCreateEmptyAppointment() throws Exception {
		assertFalse(appMgr.createAppointment(AppointmentDetails.create()));
	}

	@Test
	@InSequence(7)
	public void testCreateConflictCreatorAppointment() throws Exception {
		assertFalse(appMgr.createAppointment(appDuringDate));
		assertEquals(appMgr.showAppointmentsOfDay(userDetails, date).size(), 1);
	}

	@Test
	@InSequence(8)
	public void testCreateFreeConflictAppointment() throws Exception {
		appDuringDate.setStatus(AppointmentStatus.FREE.toString());
		assertTrue(appMgr.createAppointment(appDuringDate));
		assertEquals(appMgr.showAppointmentsOfDay(userDetails, date).size(), 2);
	}
}

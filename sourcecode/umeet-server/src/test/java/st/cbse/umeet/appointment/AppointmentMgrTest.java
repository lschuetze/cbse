package st.cbse.umeet.appointment;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import st.cbse.umeet.datatype.Appointment;
import st.cbse.umeet.dto.AppointmentDetails;

@RunWith(Arquillian.class)
public class AppointmentMgrTest {
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(AppointmentMgr.class.getPackage())
				.addPackage(Appointment.class.getPackage())
				.addPackage(AppointmentDetails.class.getPackage())
				.addAsResource("resources-jboss-managed/persistence-test.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	IAppointmentMgt appMgr;

	@PersistenceContext
	EntityManager em;

	@Inject
	UserTransaction utx;

	Appointment appBeforeDate;
	Appointment appAfterDate;
	Appointment appDuringDate;
	Calendar cal = GregorianCalendar.getInstance();
	final int YEAR = 2013;
	final int MONTH = 5;
	final int DAY = 4;

	// Prepare database
	@Test
	@InSequence(0)
	public void insertData() throws Exception {
		// Prepare dates
		cal.set(YEAR, MONTH, DAY, 0, 0, 0);
		Long date = cal.getTimeInMillis();
		cal.set(YEAR, MONTH, DAY + 1, 0, 0, 0);
		Long dateAfter = cal.getTimeInMillis();
		cal.set(YEAR, MONTH, DAY - 1, 0, 0, 0);
		Long dateBefore = cal.getTimeInMillis();
		System.out.println("#foo# date:" + date + " dateAfter:" + dateAfter + " dateBefore" + dateBefore);

		// Insert appointments into database
		utx.begin();
		em.joinTransaction();
		appBeforeDate = new Appointment("BeforeDate", dateBefore, dateBefore + 10000,
				null, null, null);
		appAfterDate = new Appointment("AfterDate", dateAfter, dateAfter + 10000,
				null, null, null);
		appDuringDate = new Appointment("Date", date, date + 10000, null, null,
				null);
		em.persist(appBeforeDate);
		em.persist(appAfterDate);
		em.persist(appDuringDate);
		utx.commit();
	}

	// tests go here
	@Test
	@InSequence(1)
	public void testDate() throws Exception {
		// Appointment app = new Appointment();
		// em.persist(app);
		utx.begin();
		em.joinTransaction();
		cal.set(YEAR, MONTH, DAY, 0, 0, 0);
		// Long day1 = cal.getTimeInMillis();
		// cal.set(YEAR, MONTH, DAY+1);
		// Long day2 = cal.getTimeInMillis();
		// System.out.println(day2-day1);
		System.out.println(appMgr.showAppointmentsOfDay(null, cal.getTimeInMillis())
				.size());
		assertEquals(appMgr.showAppointmentsOfDay(null, cal.getTimeInMillis())
				.size(), 1);
		utx.commit();
		
	}

	@Test
	@InSequence(2)
	public void testDateAfter() throws Exception {
		cal.set(YEAR, MONTH, DAY + 1, 0, 0, 0);
			assertEquals(
					appMgr.showAppointmentsOfDay(null, cal.getTimeInMillis())
							.size(), 1);
	}

	@Test
	@InSequence(3)
	public void testDateAfterEmpty() throws Exception {
		cal.set(YEAR, MONTH, DAY + 2, 0, 0, 0);
		assertEquals(appMgr.showAppointmentsOfDay(null, cal.getTimeInMillis())
				.size(), 0);
	}

	@Test
	@InSequence(4)
	public void testDateBefore() throws Exception {
		cal.set(YEAR, MONTH, DAY - 1, 0, 0, 0);
		assertEquals(appMgr.showAppointmentsOfDay(null, cal.getTimeInMillis())
				.size(), 1);
	}

	@Test
	@InSequence(5)
	public void testDateBeforeEmpty() throws Exception {
		cal.set(YEAR, MONTH, DAY + 2, 0, 0, 0);
		assertEquals(appMgr.showAppointmentsOfDay(null, cal.getTimeInMillis())
				.size(), 0);
	}
}

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
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import st.cbse.umeet.datatype.Appointment;
import st.cbse.umeet.dto.AppointmentDetails;

@RunWith(Arquillian.class)
public class AppointmentMgrTest {
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(AppointmentMgr.class.getPackage())
            .addPackage(Appointment.class.getPackage())
            .addPackage(AppointmentDetails.class.getPackage())
            .addAsResource("resources-jboss-managed/persistence-test.xml", "META-INF/persistence.xml")
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
    int YEAR = 2013;
    int MONTH = 5;
    int DAY = 4;
    
 
    
    @Before
    public void preparePersistenceTest() throws Exception {
    	cal.set(YEAR, MONTH, DAY, 0, 0, 0);
    	Long date = cal.getTimeInMillis();
    	cal.set(YEAR, MONTH, DAY+1);
    	Long dateAfter = cal.getTimeInMillis();
    	cal.set(YEAR, MONTH, DAY-1);
    	Long dateBefore = cal.getTimeInMillis();
    	startTransaction();
    	appBeforeDate = new Appointment(null, dateBefore, dateBefore+10000, null, null, null);
    	appAfterDate = new Appointment(null, dateAfter, dateAfter+10000, null, null, null);
    	appDuringDate = new Appointment(null, date, date+10000, null, null, null);
    	em.persist(appBeforeDate);
    	em.persist(appAfterDate);
    	em.persist(appDuringDate);
    	utx.commit();
    }
    @After
    public void commitTransaction() throws Exception {
    }
    
    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }
 
    // tests go here
    @Test
    public void testDate() throws Exception {
//    	Appointment app = new Appointment();
//    	em.persist(app);
    	cal.set(YEAR, MONTH, DAY);
//    	Long day1 = cal.getTimeInMillis();
//    	cal.set(YEAR, MONTH, DAY+1);
//    	Long day2 = cal.getTimeInMillis();
//    	System.out.println(day2-day1);
    	assertEquals(appMgr.showAppointmentsOfDay(null, cal.getTimeInMillis()).size(), 1);
    }
}

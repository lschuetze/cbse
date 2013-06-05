package st.cbse.umeet.datatype;

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

@RunWith(Arquillian.class)
public class UserTest {
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(User.class.getPackage())
            .addAsResource("resources-jboss-managed/persistence-test.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
        insertData();
        startTransaction();
    }

    private void clearData() throws Exception {
//        utx.begin();
//        em.joinTransaction();
//        System.out.println("Dumping old records...");
//        em.createQuery("delete from Game").executeUpdate();
//        utx.commit();
    }

    private void insertData() throws Exception {
//        utx.begin();
//        em.joinTransaction();
//        System.out.println("Inserting records...");
//        for (String title : GAME_TITLES) {
//            Game game = new Game(title);
//            em.persist(game);
//        }
//        utx.commit();
//        // clear the persistence context (first-level cache)
//        em.clear();
    }

    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }
    
    @After
    public void commitTransaction() throws Exception {
        utx.commit();
    }
 
    // tests go here
    @Test
    public void testDummy() throws Exception {
    	System.out.println("fooo");
    	User user = new User();
    	user.setEmail("foo");
    	em.persist(user);
    }
}

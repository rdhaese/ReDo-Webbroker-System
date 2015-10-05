import org.junit.Test;
import org.realdolmen.webbroker.model.AirlineCompany;
import org.realdolmen.webbroker.model.user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
public class AbcTest {

    @Test
    public void sdpfjiosdfsdfTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyTestPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        AirlineCompany ac = new AirlineCompany();
        ac.setName("blabla");
        User u = new User();
        u.setFirstName("sd");
        u.setLastName("dsf");
        u.setUserName("sqd");
        u.setPassword("sdfs");
        em.persist(u);
        em.persist(ac);
        t.commit();
        em.close();
        emf.close();
    }
}

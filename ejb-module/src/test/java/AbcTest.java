import org.junit.Test;
import org.realdolmen.webbroker.model.AirlineCompany;

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
        em.persist(ac);
        t.commit();
        em.close();
        emf.close();
    }
}

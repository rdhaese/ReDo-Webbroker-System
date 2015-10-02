import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
public class DBUnitTest extends PersistenceTest {

    @Test
    public void testDBunit() throws Exception{
//        EntityManager em = getEmf().createEntityManager();
//        EntityTransaction t = em.getTransaction();
//        t.begin();
        assertEquals(2, entityManager().createQuery("SELECT a FROM AirlineCompany a").getResultList().size());
//        t.commit();
//        em.close();
//        getEmf().close();
    }
}

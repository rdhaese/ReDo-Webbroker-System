package org.realdolmen.webbroker.repository;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.exception.AmbiguousEntityException;
import org.realdolmen.webbroker.model.TravelAgency;

/**
 * Persistence test for the {@link TravelAgencyRepository}.
 *
 * @author Youri Flement
 */
public class TravelAgencyRepositoryTest extends DataSetPersistenceTest {

    TravelAgencyRepository repository;

    @Before
    public void setup() {
        repository = new TravelAgencyRepository();
        repository.entityManager = entityManager();
    }

    @Test
    public void canFindTravelAgencyiesName() throws Exception {
        assertEquals(1, repository.getTravelAgenciesByName("Jetair").size());
        entityManager().persist(new TravelAgency("Jetair"));
        assertEquals(2, repository.getTravelAgenciesByName("Jetair").size());

        assertEquals(0, repository.getTravelAgenciesByName("non existing agency").size());
    }

    @Test
    public void canFindASingleTravelAgency() throws Exception {
        assertNotNull(repository.getSingleTravelAgency("Jetair"));
        assertNull(repository.getSingleTravelAgency("non existing agency"));
    }

    @Test(expected = AmbiguousEntityException.class)
    public void cannotFindAmbiguousTravelAgency() throws Exception {
        entityManager().persist(new TravelAgency("Jetair"));
        entityManager().persist(new TravelAgency("Jetair"));

        repository.getSingleTravelAgency("Jetair");
    }

}

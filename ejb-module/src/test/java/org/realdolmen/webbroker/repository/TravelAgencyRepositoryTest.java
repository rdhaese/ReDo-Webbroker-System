package org.realdolmen.webbroker.repository;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;

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
    public void canFindTravelAgencyByName() throws Exception {
        assertEquals(1, repository.getTravelAgenciesByName("Jetair").size());
        assertEquals(0, repository.getTravelAgenciesByName("non existing agency").size());
    }

}

package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;


/**
 * Created by RDEAX37 on 5/10/2015.
 * Persistence tests for {@link Address}
 * @author Robin D'Haese
 */
public class TravelAgencyPersistenceTest extends DataSetPersistenceTest{

    TravelAgency ta;

    @Before
    public void setUp(){
        createTA();
    }

    private void createTA(){
        ta = new TravelAgency();
        ta.setName("Neckerman");
    }


    @Test
    public void canTravelAgencyBePersisted(){
        assertNull(ta.getId());
        entityManager().persist(ta);
        assertNotNull(ta.getId());
    }

    @Test (expected = ConstraintViolationException.class)
    public void travelAgencyCantBePersistedWithoutName(){
        ta.setName(null);
        entityManager().persist(ta);
    }

    @Test
    public void canAllTravelAgenciesBeFound(){
        assertEquals(2, entityManager().createQuery("SELECT t FROM TravelAgency t", TravelAgency.class).getResultList().size());
    }

    @Test
    public void canTravelAgencyBeFoundOnId(){
        assertNotNull(entityManager().find(TravelAgency.class, 1L));
    }
}

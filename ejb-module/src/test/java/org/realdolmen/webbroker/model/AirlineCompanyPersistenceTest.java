package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

/**
 * Created by RDEAX37 on 5/10/2015.
 * Persistence tests for {@link AirlineCompany}
 * @author Robin D'Haese
 */
public class AirlineCompanyPersistenceTest extends DataSetPersistenceTest{

    AirlineCompany ac;

    @Before
    public void setUp(){
        createAC();
    }

    private void createAC(){
        ac = new AirlineCompany();
        ac.setName("Sabena");
    }


    @Test
    public void canAirlineCompanyBePersisted(){
        assertNull(ac.getId());
        entityManager().persist(ac);
        assertNotNull(ac.getId());
    }

    @Test (expected = ConstraintViolationException.class)
    public void airlineCompanyCantBePersistedWithoutName(){
        ac.setName(null);
        entityManager().persist(ac);
    }

    @Test
    public void canAllAirlineCompaniesBeFound(){
        assertEquals(2, entityManager().createQuery("SELECT a FROM AirlineCompany  a", AirlineCompany.class).getResultList().size());
    }

    @Test
    public void canAirlineCompanyBeFoundOnId(){
        assertNotNull(entityManager().find(AirlineCompany.class, 1L));
    }
}

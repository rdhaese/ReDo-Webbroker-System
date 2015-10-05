package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.model.user.AirlineCompanyEmployee;
import org.realdolmen.webbroker.model.user.TravelAgencyEmployee;
import org.realdolmen.webbroker.model.user.User;

import javax.validation.ConstraintViolationException;

/**
 * Created by RDEAX37 on 5/10/2015.
 */
public class TravelAgencyEmployeeTest extends DataSetPersistenceTest {

    private TravelAgencyEmployee user;


    @Before
    public void setUp() {
        createUser("Robin", "D'Haese", "rdhaese", "password1");
    }

    private void createUser(String lName, String fName, String uName, String password) {
        user = new TravelAgencyEmployee();
        user.setFirstName(lName);
        user.setLastName(fName);
        user.setUserName(uName);
        user.setPassword(password);
        user.setTravelAgency(getTravelAgency());
    }

    private TravelAgency getTravelAgency() {
        TravelAgency ta = new TravelAgency();
        ta.setName("T Agency");
        return ta;
    }

    @Test
    public void testCanTravelEmployeeBePersisted(){
        int originalSize = entityManager().createQuery("SELECT u From TravelAgencyEmployee u", TravelAgencyEmployee.class).getResultList().size();
        entityManager().persist(user);
        assertEquals(originalSize + 1, entityManager().createQuery("SELECT u From TravelAgencyEmployee u", TravelAgencyEmployee.class).getResultList().size());

    }

    @Test (expected = ConstraintViolationException.class)
    public void testTravelEmployeeCantBePersistedWithoutAirlineCompany(){
        user.setTravelAgency(null);
        entityManager().persist(user);
    }

    @Test
    public void testTravelEmployeesCanBeFound(){
        assertEquals(2, entityManager().createQuery("SELECT u FROM TravelAgencyEmployee u", TravelAgencyEmployee.class).getResultList().size());

    }
}

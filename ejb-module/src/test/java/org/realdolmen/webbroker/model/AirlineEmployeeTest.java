package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.model.user.AirlineCompanyEmployee;
import org.realdolmen.webbroker.model.user.User;

import javax.validation.ConstraintViolationException;

/**
 * Created by RDEAX37 on 5/10/2015.
 */
public class AirlineEmployeeTest extends DataSetPersistenceTest {

    private AirlineCompanyEmployee user;


    @Before
    public void setUp() {
        createUser("Robin", "D'Haese", "rdhaese", "password1");
    }

    private void createUser(String lName, String fName, String uName, String password) {
        user = new AirlineCompanyEmployee();
        user.setFirstName(lName);
        user.setLastName(fName);
        user.setUserName(uName);
        user.setPassword(password);
        user.setCompany(getCompany());
    }

    private AirlineCompany getCompany() {
        AirlineCompany ac = new AirlineCompany();
        ac.setName("AC Company");
        return ac;
    }

    @Test
    public void testCanAirlineEmployeeBePersisted(){
        int originalSize = entityManager().createQuery("SELECT u From AirlineCompanyEmployee u", AirlineCompanyEmployee.class).getResultList().size();
        entityManager().persist(user);
        assertEquals(originalSize + 1, entityManager().createQuery("SELECT u From AirlineCompanyEmployee u", AirlineCompanyEmployee.class).getResultList().size());
    }

    @Test (expected = ConstraintViolationException.class)
    public void testAirlineEmployeeCantBePersistedWithoutAirlineCompany(){
        user.setCompany(null);
        entityManager().persist(user);
    }

    @Test
    public void testAirlineEmployeesCanBeFound(){
        assertEquals(2, entityManager().createQuery("SELECT u FROM AirlineCompanyEmployee u", AirlineCompanyEmployee.class).getResultList().size());
    }
}

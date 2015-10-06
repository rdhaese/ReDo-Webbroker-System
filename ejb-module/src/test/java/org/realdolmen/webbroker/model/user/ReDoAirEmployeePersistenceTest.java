package org.realdolmen.webbroker.model.user;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;

/**
 * Created by RDEAX37 on 6/10/2015.
 */
public class ReDoAirEmployeePersistenceTest extends DataSetPersistenceTest{

    ReDoAirEmployee emp;

    @Before
    public void setUp(){
        createReDoAirEmployee();
    }

    private void createReDoAirEmployee() {
        emp = new ReDoAirEmployee();
        emp.setFirstName("Robin");
        emp.setLastName("D'Haese");
        emp.setUserName("rdhaese");
        emp.setPassword("123456");
    }

    @Test
    public void canReDoAirEmployeeBePersisted(){
        int originalSize = entityManager().createQuery("SELECT r From ReDoAirEmployee r", ReDoAirEmployee.class).getResultList().size();
        entityManager().persist(emp);
        assertEquals(originalSize + 1, entityManager().createQuery("SELECT r From ReDoAirEmployee r", ReDoAirEmployee.class).getResultList().size());
    }

    @Test
    public void canRedoAirEmployeesBeFound(){
        assertEquals(2, entityManager().createQuery("SELECT r From ReDoAirEmployee r", ReDoAirEmployee.class).getResultList().size());
    }

    @Test
    public void canReDoAirEmployeeBeFoundOnId(){
        assertNotNull(entityManager().find(ReDoAirEmployee.class, 9L));
    }
}

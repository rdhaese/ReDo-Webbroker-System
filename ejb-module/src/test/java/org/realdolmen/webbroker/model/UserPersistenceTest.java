package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.model.user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;

/**
 * Created by RDEAX37 on 5/10/2015.
 */
public class UserPersistenceTest extends DataSetPersistenceTest {

    private User user;

    @Before
    public void setUp() {
        createUser("Robin", "D'Haese", "rdhaese", "password1");
    }

    private void createUser(String lName, String fName, String uName, String password) {
        user = new User();
        user.setFirstName(lName);
        user.setLastName(fName);
        user.setUserName(uName);
        user.setPassword(password);
    }

    @Test
    public void testCanUserBePersisted() throws Exception {
        int originalSize = entityManager().createQuery("SELECT u From users u", User.class).getResultList().size();
        entityManager().persist(user);
        assertEquals(originalSize + 1, entityManager().createQuery("SELECT u From users u", User.class).getResultList().size());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testUserCantBePersistedWithoutLastName() {
        user.setLastName(null);
        entityManager().persist(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testUserCantBePersistedWithoutFirstName() {
        user.setFirstName(null);
        entityManager().persist(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testUserCantBePersistedWithoutUserName() {
        user.setUserName(null);
        entityManager().persist(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testUserCantBePersistedWithoutPassword() {
        user.setPassword(null);
        entityManager().persist(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUserPasswordShouldBeAtLeast6Characters() {
        user.setPassword("123");
    }

    @Test
    public void testCanAllUsersBeFound() {
        assertEquals(8, entityManager().createQuery("SELECT u FROM users u", User.class).getResultList().size());
    }

    @Test
    public void testCanUserBeFoundOnId() {
        assertEquals("Fred", entityManager().find(User.class, 1L).getFirstName());
        assertEquals("adetest", entityManager().find(User.class, 4L).getUserName());
    }

}

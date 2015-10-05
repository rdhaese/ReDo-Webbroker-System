package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.PersistenceTest;
import org.realdolmen.webbroker.model.user.User;

import javax.persistence.EntityManager;
import java.time.Period;

/**
 * Created by RDEAX37 on 5/10/2015.
 */
public class UserPersistenceTest extends PersistenceTest {

    private User user;

    @Before
    public void setUp(){
        createUser();
    }

    private void createUser() {
        user = new User();
        user.setFirstName("Robin");
        user.setLastName("D'Haese");
        user.setUserName("rdhaese");
        user.setPassword("123456");
    }

    @Test
    public void testCanUserBePersisted() throws Exception {
        int originalSize = entityManager().createQuery( "SELECT u From users u", User.class).getResultList().size();
        entityManager().persist(user);
        int expectedSize = originalSize + 1;
        int actualSize = entityManager().createQuery("SELECT u From users u", User.class).getResultList().size();
        assertEquals(expectedSize,actualSize);
    }

    @Test
    public void testUserCantBePersistedWithoutLastName(){

    }

    @Test
    public void testUserCantBePersistedWithoutFirstName(){

    }

    @Test
    public void testUserCantBePersistedWithoutUserName(){

    }

    @Test
    public void testUserCantBePersistedWithoutPassword(){

    }

    @Test
    public void testCanAllUsersBeFound(){

    }

    @Test
    public void testCanUserBeFoundOnId(){

    }
}

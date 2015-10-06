package org.realdolmen.webbroker.repository;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.model.user.User;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

/**
 * Created by RDEAX37 on 5/10/2015.
 */
public class UserRepositoryTest extends DataSetPersistenceTest {

    private UserRepository userRepository;
    private User user;
    @Before
    public void setUp() throws Exception {
        userRepository = new UserRepository();
        userRepository.setEntityManager(entityManager());
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
    public void canUserBeAdded(){
        assertNull(user.getId());
        entityManager().persist(user);
        assertNotNull(user.getId());
    }

    @Test
    public void canUserBeUpdated(){
        userRepository.add(user);
        String newName = "NewLastName";
        user.setLastName(newName);
        userRepository.update(user);
        assertEquals(newName, userRepository.find(user.getId()).getLastName());
    }

    @Test
    public void canUserBeFoundOnId(){
        assertNotNull(userRepository.find(1L));
    }

    @Test
    public void canAllUsersBeFound(){
        assertEquals(8, userRepository.getAllUsers().size());
    }

    @Test
    public void canUserBeRemoved(){
        userRepository.add(user);
        assertNotNull(userRepository.find(user.getId()));
        userRepository.remove(user);
        assertNull(userRepository.find(user.getId()));
    }

    @Test
    public void canUserBeFoundOnUserName(){
        assertNotNull(userRepository.getUserByUsername("gdevlieger"));
    }
}

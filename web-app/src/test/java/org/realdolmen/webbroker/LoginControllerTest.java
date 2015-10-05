package org.realdolmen.webbroker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.realdolmen.webbroker.controller.LoggedInUserController;
import org.realdolmen.webbroker.controller.LoginController;
import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoggedInUserController loggedInUserController;

    @InjectMocks
    private LoginController controller;

    @Test
    public void userCanLogin() throws Exception {
        User userFromDatabase = new User();
        userFromDatabase.setUserName("root");
        userFromDatabase.setPassword("root");
        when(userRepository.getUserByUsername("root")).thenReturn(userFromDatabase);
        controller.setUsername(userFromDatabase.getUserName());
        controller.setPassword(userFromDatabase.getPassword());

        assertEquals("index", controller.login());
    }

    @Test
    public void userCannotLoginWithWrongCredentials() throws Exception {
        User userFromDatabase = new User();
        userFromDatabase.setUserName("root");
        userFromDatabase.setPassword("password");
        when(userRepository.getUserByUsername("root")).thenReturn(userFromDatabase);

        // Login with wrong password
        controller.setUsername(userFromDatabase.getUserName());
        controller.setPassword("WrongEnteredPassword");
        assertEquals("loginForm", controller.login());

        // Login with wrong username
        controller.setUsername("WrongEnteredUsername");
        controller.setPassword("password");
        assertEquals("loginForm", controller.login());
    }

    @Test
    public void nonExistingUserCannotLogin() throws Exception {
        when(userRepository.getUserByUsername("root")).thenReturn(null);
        controller.setUsername("username");
        controller.setPassword("password");
        assertEquals("loginForm", controller.login());
    }

}

package org.realdolmen.webbroker.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.repository.UserRepository;
import org.realdolmen.webbroker.service.PasswordService;
import org.realdolmen.webbroker.util.Pair;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Mock test for the {@link RegisterController} to test whether a visitor can
 * register and to determine that the input data is correctly validated.
 *
 * @author Youri Flement
 */
@RunWith(MockitoJUnitRunner.class)
public class RegisterControllerTest {

    @Mock
    UserRepository userRepo;

    @Mock
    PasswordService passwordService;

    @InjectMocks
    RegisterController controller;

    @Test
    public void canRegisterUser() throws Exception {
        when(userRepo.getUserByUsername("root")).thenReturn(null);
        when(passwordService.createSecurePassword("password")).thenReturn(new Pair<>("salt","password"));

        controller.setFirstName("root");
        controller.setLastName("root");
        controller.setUserName("root");
        controller.setPassword("password");

        assertEquals("register-success", controller.registerUser());
        assertNull(controller.getErrorMessage());
    }

    @Test
    public void cannotCreateTwoUsersWithSameName() throws Exception {
        when(userRepo.getUserByUsername("root")).thenReturn(new User());
        controller.setUserName("root");

        assertEquals("register-user", controller.registerUser());
        assertNotNull(controller.getErrorMessage());
    }

}

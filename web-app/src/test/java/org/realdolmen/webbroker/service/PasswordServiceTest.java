package org.realdolmen.webbroker.service;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.util.Pair;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Unit test for the {@link PasswordService}.
 *
 * @author Youri Flement
 */
public class PasswordServiceTest {

    private PasswordService passwordService;

    private User user;

    @Before
    public void setup() {
        passwordService = new PasswordService();
        user = new User();
        user.setUserName("root");
        Pair<String, String> password = passwordService.createSecurePassword("password");
        user.setSalt(password.getFirst());
        user.setPassword(password.getSecond());
    }

    @Test
    public void canCreateSecurePassword() throws Exception {
        Pair<String, String> password = passwordService.createSecurePassword("password");
        assertNotNull(password.getFirst());
        assertNotNull(password.getSecond());
        assertFalse(password.getFirst().isEmpty());
        assertFalse(password.getSecond().isEmpty());
        assertFalse("password".equals(password.getSecond()));
    }

    @Test
    public void equalPasswordsAreCorrect() throws Exception {
        passwordService.isCorrectPassword(user, "password");
    }

    @Test
    public void differentPasswordsAreIncorrect() throws Exception {
        passwordService.isCorrectPassword(user, "wrong password");
        passwordService.isCorrectPassword(user, null);
        passwordService.isCorrectPassword(user, "");
    }

    @Test
    public void nonExistingUserHasNoCorrectPassword() throws Exception {
        assertFalse(passwordService.isCorrectPassword(null, ""));
    }

}

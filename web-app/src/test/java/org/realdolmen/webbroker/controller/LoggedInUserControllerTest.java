package org.realdolmen.webbroker.controller;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.model.user.User;

import static org.junit.Assert.*;

public class LoggedInUserControllerTest {

    protected LoggedInUserController controller;

    @Before
    public void setup() throws Exception {
        controller = new LoggedInUserController();
    }

    @Test
    public void userCanBeLoggedIn() throws Exception {
        assertFalse(controller.isLoggedIn());

        // login a user
        User user = new User();
        controller.setLoggedInUser(user);
        assertTrue(controller.isLoggedIn());
        assertEquals(user, controller.getLoggedInUser());
    }

    @Test
    public void testUserCanLogout() throws Exception {
        controller.setLoggedInUser(new User());
        assertEquals("index", controller.logout());
    }

    @Test
    public void nonExistingUserLogoutDoesNotBreakApplication() throws Exception {
        controller.setLoggedInUser(null);
        assertEquals("index", controller.logout());
    }

}

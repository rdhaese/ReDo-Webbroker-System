package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Controller which keeps tracked of the logged in user and provides a method to log him out.
 *
 * @author Youri Flement
 */
@Named
@SessionScoped
public class LoggedInUserController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggedInUserController.class);

    private User loggedInUser;

    /**
     * Get the logged in user. This can return <code>null</code> if the user has not yet logged in. The logged-in
     * status is remembered during the session, so closing the browser will also logout the user.
     *
     * @return the logged in <code>User</code> or <code>null</code> if the user is not logged in.
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Set the logged in user. The logged-in status will be remembered for the current session or until the user
     * logs out.
     *
     * @param loggedInUser The user which wants to login.
     */
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    /**
     * Determine whether the visitor is currently logged in or not.
     *
     * @return <code>true</code> if the visitor is logged in, <code>false</code> otherwise.
     */
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    /**
     * Logout the current user and return to the homepage.
     *
     * @return The homepage.
     */
    public String logout() {
        if(loggedInUser == null) {
            LOGGER.warn("Logout of user even though there is no logged in user.");
        } else {
            LOGGER.info("User '" + loggedInUser.getUserName() + "' has logged out.");
        }
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        loggedInUser = null;
        return "index";
    }

    public String getRoll(){
        return loggedInUser.getClass().getSimpleName();
    }
}

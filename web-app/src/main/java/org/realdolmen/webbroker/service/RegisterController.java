package org.realdolmen.webbroker.service;

import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.repo.GenericRepo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by RDEAX37 on 5/10/2015.
 */
@Named
@RequestScoped
public class RegisterController {

    @Inject
    private GenericRepo<User> userRepo;

    public String registerPassenger(){


        return "TODO";
    }

}

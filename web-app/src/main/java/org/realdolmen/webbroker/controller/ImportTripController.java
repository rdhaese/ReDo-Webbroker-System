package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.repository.TripRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RDEAX37 on 7/10/2015.
 */

@Named
@RequestScoped
public class ImportTripController {

    @NotNull
    private Part file;

    @Inject
    private TripRepository tripRepo;


    public void importFile(){


    }

    public void validate(FacesContext ctx,
                             UIComponent comp,
                             Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part)value;
        if (file.getSize() > 1024) {
            msgs.add(new FacesMessage("File is too big"));
        }
        if (!"text/xml".equals(file.getContentType())) {
            msgs.add(new FacesMessage("Not a XML file"));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
}

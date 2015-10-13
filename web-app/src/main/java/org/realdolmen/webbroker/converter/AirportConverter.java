package org.realdolmen.webbroker.converter;

import org.realdolmen.webbroker.model.Airport;
import org.realdolmen.webbroker.repository.AirportRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * Created by RDEAX37 on 6/10/2015.
 *
 * JSF Converter to convert airports from objects to id's and id's to objects.
 * @author Robin D'Haese
 */
@ManagedBean(name = "flightConverterBean")
@FacesConverter(value = "flightConverter")
public class AirportConverter implements Converter {

    @Inject
    private AirportRepository airportRepo;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return airportRepo.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Airport)value).getName();
    }
}

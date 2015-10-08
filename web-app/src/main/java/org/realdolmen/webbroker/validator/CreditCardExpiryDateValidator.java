package org.realdolmen.webbroker.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@FacesValidator("org.realdolmen.CreditCardExpiryDateValidator")
public class CreditCardExpiryDateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String dateString = (String) value;

        if(dateString == null || dateString.length() != 5) {
            throw new ValidatorException(new FacesMessage("Credit card expiry date must have 5 characters"));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        try {
            LocalDate parsedDate = LocalDate.parse("01/" + dateString, formatter);
            if(parsedDate.isBefore(LocalDate.now())) {
                throw new ValidatorException(new FacesMessage("Credit card has expired"));
            }
        } catch (DateTimeParseException e) {
            throw new ValidatorException(new FacesMessage("Credit card expiry date must follow the pattern: dd/MM. For example: 05/20"));
        }
    }


}

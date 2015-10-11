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
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Validator for a credit card expiry date.
 *
 * @author Youri Flement
 */
@FacesValidator("org.realdolmen.CreditCardExpiryDateValidator")
public class CreditCardExpiryDateValidator implements Validator {

    private ResourceBundle bundle;
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String dateString = (String) value;

        if(dateString == null || dateString.length() != 5) {
            String message = "Credit card expiry date must have 5 characters";
           try{
                message = getBundle().getString("ccDateValidator.notEnoughCharacters");
            } catch (Exception e){}
            throw new ValidatorException(new FacesMessage(message));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        try {
            LocalDate parsedDate = LocalDate.parse("01/" + dateString, formatter);
            if(parsedDate.isBefore(LocalDate.now())) {
                String message = "ccDateValidator.expired";
               try {
                    message = getBundle().getString("ccDateValidator.expired");
                } catch (Exception e){}
                throw new ValidatorException(new FacesMessage(message));
            }
        } catch (DateTimeParseException e) {
            String message = "Credit card expiry date must follow the pattern: MM/yy. For example: 05/20";
            try{
                message =  getBundle().getString("ccDateValidator.wrongFormat");
            } catch (Exception ex){}
            throw new ValidatorException(new FacesMessage(message));
        }
    }

    private ResourceBundle getBundle(){
        if (bundle == null){
            Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            bundle = ResourceBundle.getBundle("messages.text", locale);
        }
        return bundle;
    }
}

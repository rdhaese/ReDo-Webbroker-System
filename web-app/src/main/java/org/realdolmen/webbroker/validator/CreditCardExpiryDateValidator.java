package org.realdolmen.webbroker.validator;

import org.realdolmen.webbroker.i18n.Text;

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

    public CreditCardExpiryDateValidator(){
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        bundle = ResourceBundle.getBundle("messages.text", locale);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String dateString = (String) value;

        if(dateString == null || dateString.length() != 5) {
            throw new ValidatorException(new FacesMessage(bundle.getString("ccDateValidator.notEnoughCharacters")));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        try {
            LocalDate parsedDate = LocalDate.parse("01/" + dateString, formatter);
            if(parsedDate.isBefore(LocalDate.now())) {
                throw new ValidatorException(new FacesMessage(bundle.getString("ccDateValidator.expired")));
            }
        } catch (DateTimeParseException e) {
            throw new ValidatorException(new FacesMessage(bundle.getString("ccDateValidator.wrongFormat")));
        }
    }

}

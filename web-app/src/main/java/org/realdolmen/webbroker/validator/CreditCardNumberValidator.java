package org.realdolmen.webbroker.validator;


import org.realdolmen.webbroker.i18n.Text;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Validator for a credit card number. Uses Luhn's algorithm to determine if the credit card number is valid.
 *
 * @author Youri Flement
 */
@FacesValidator("org.realdolmen.CreditCardNumberValidator")
public class CreditCardNumberValidator implements Validator {

    private ResourceBundle bundle;

    public CreditCardNumberValidator(){
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        bundle = ResourceBundle.getBundle("messages.text", locale);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value == null || !isValid((String) value)) {
            throw new ValidatorException(new FacesMessage(bundle.getString("ccNumberValidator.invalid")));
        }
    }

    private boolean isValid(String ccNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--) {
            try {
                int n = Integer.parseInt(ccNumber.substring(i, i + 1));
                if (alternate) {
                    n *= 2;
                    if (n > 9) {
                        n = (n % 10) + 1;
                    }
                }
                sum += n;
                alternate = !alternate;
            } catch (NumberFormatException e) {
                return false;
            }

        }
        return (sum % 10 == 0);
    }
}

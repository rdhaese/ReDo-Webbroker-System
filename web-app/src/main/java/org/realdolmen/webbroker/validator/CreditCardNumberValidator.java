package org.realdolmen.webbroker.validator;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("org.realdolmen.CreditCardNumberValidator")
public class CreditCardNumberValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String creditCardNumber = (String)value;
        if(!isValid(creditCardNumber)) {
            FacesMessage message = new FacesMessage("cc validation");
            throw new ValidatorException(message);
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

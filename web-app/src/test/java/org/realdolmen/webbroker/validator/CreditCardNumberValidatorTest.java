package org.realdolmen.webbroker.validator;

import org.junit.Before;
import org.junit.Test;

import javax.faces.validator.ValidatorException;

/**
 * Unit test for the {@link CreditCardNumberValidator}.
 *
 * @author Youri Flement
 */
public class CreditCardNumberValidatorTest {

    private CreditCardNumberValidator validator;

    @Before
    public void setup() {
        validator = new CreditCardNumberValidator();
    }

    @Test(expected = ValidatorException.class)
    public void invalidCardNumberThrowsMessage() throws Exception {
        validator.validate(null, null, "sdqsdazd");
    }

    @Test(expected = ValidatorException.class)
    public void invalidStringNumberThrowsMessage() throws Exception {
        validator.validate(null, null, "4556049969119425");
    }

    @Test(expected = ValidatorException.class)
    public void invalidInputThrowsMessage() throws Exception {
        validator.validate(null, null, null);
    }

    @Test
    public void validCreditcardNumberThrowsNoException() throws Exception {
        validator.validate(null, null, "4556049969119426");
    }

}

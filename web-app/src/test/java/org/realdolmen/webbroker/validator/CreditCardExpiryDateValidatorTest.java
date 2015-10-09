package org.realdolmen.webbroker.validator;

import org.junit.Before;
import org.junit.Test;

import javax.faces.validator.ValidatorException;

/**
 * Unit test for the {@link CreditCardExpiryDateValidator}.
 *
 * @author Youri Flement
 */
public class CreditCardExpiryDateValidatorTest {

    private CreditCardExpiryDateValidator validator;

    @Before
    public void setup() {
        validator = new CreditCardExpiryDateValidator();
    }

    @Test(expected = ValidatorException.class)
    public void invalidInputThrowsMessage() throws Exception {
        validator.validate(null, null, null);
    }

    @Test(expected = ValidatorException.class)
    public void invalidPatternThrowsMessage() throws Exception {
        validator.validate(null, null, "01-16");
    }

    @Test(expected = ValidatorException.class)
    public void expiredCreditCardThrowsMessage() throws Exception {
        validator.validate(null, null, "01/10");
    }

    @Test
    public void validExpiryDateThrowsNoMessage() throws Exception {
        // this will only work until 2099
        validator.validate(null, null, "01/99");
    }

}

package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;

import javax.validation.ConstraintViolationException;

/**
 *
 * Persistence test for the {@link Discount} entity.
 *
 * @author Youri Flement
 */
public class DiscountPersistenceTest extends DataSetPersistenceTest {

    private Discount discount;

    @Before
    public void setup() throws Exception {
        discount = new Discount("50 percent off", 50d, true);
    }

    @Test
    public void discountCanBePersisted() throws Exception {
        entityManager().persist(discount);
        assertNotNull(discount.getId());
    }

    @Test
    public void canFindAllDiscounts() throws Exception {
        assertEquals(2, entityManager().createQuery("select d from Discount d").getResultList().size());
    }

    @Test
    public void canFindDiscountById() throws Exception {
        assertNotNull(entityManager().find(Discount.class, 5000l));
    }

    @Test(expected = ConstraintViolationException.class)
    public void discountMustHaveName() throws Exception {
        discount.setName(null);
        entityManager().persist(discount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void quantityMustBePositive() throws Exception {
        discount.setQuantity(-1d);
    }

    @Test(expected = ConstraintViolationException.class)
    public void discountMustHaveQuantity() throws Exception {
        discount.setQuantity(null);
        entityManager().persist(discount);
    }

    @Test(expected = ConstraintViolationException.class)
    public void discountMustHavePercentage() throws Exception {
        discount.setIsPercentage(null);
        entityManager().persist(discount);
    }
}

package org.realdolmen.webbroker.repository;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.model.Discount;
import org.realdolmen.webbroker.util.EntityFactory;

/**
 * Created by RDEAX37 on 10/10/2015.
 */
public class DiscountRepositoryTest extends DataSetPersistenceTest {

    private DiscountRepository discountRepository;
    private Discount discount;

    @Before
    public void setUp(){
        discountRepository = new DiscountRepository();
        discountRepository.setEntityManager(entityManager());
        discount = EntityFactory.createDiscount();
    }

    @Test
    public void canDiscountBeAdded(){
        assertNull(discount.getId());
        discountRepository.add(discount);
        assertNotNull(discount.getId());
    }

    @Test
    public void canDiscountBeFoundOnName(){
        assertNotNull(discountRepository.getOnName("free"));
    }
}

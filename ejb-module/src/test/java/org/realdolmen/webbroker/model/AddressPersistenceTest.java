package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.model.user.User;

import javax.validation.ConstraintViolationException;

/**
 * Created by RDEAX37 on 5/10/2015.
 * Persistence tests for {@link Address}
 * @author Robin D'Haese
 */
public class AddressPersistenceTest extends DataSetPersistenceTest {

    private Address address;
    @Before
    public void setUp() {
        createAddress("Belgium", "Schendelbeke", "9506", "Dagmoedstraat", "77" );
    }

    private void createAddress(String country, String city, String postalCode, String street, String number) {
       address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setPostalCode(postalCode);
        address.setStreet(street);
        address.setNumber(number);
    }

    @Test
    public void canAddressBePersisted(){
        int originalSize = entityManager().createQuery("SELECT a From Address a", Address.class).getResultList().size();
        entityManager().persist(address);
        assertEquals(originalSize + 1, entityManager().createQuery("SELECT a From Address a", Address.class).getResultList().size());
    }

    @Test (expected = ConstraintViolationException.class)
    public void addressCantBePersistedWithoutCountry(){
        address.setCountry(null);
        entityManager().persist(address);
    }

    @Test (expected = ConstraintViolationException.class)
    public void addressCantBePersistedWithoutStreet(){
        address.setStreet(null);
        entityManager().persist(address);
    }

    @Test (expected = ConstraintViolationException.class)
    public void addressCantBePersistedWithoutNumber(){
        address.setNumber(null);
        entityManager().persist(address);
    }

    @Test (expected = ConstraintViolationException.class)
    public void addressCantBePersistedWithoutPostalCode(){
        address.setPostalCode(null);
        entityManager().persist(address);
    }

    @Test (expected = ConstraintViolationException.class)
    public void addressCantBePersistedWithoutCity(){
        address.setCity(null);
        entityManager().persist(address);
    }

    @Test
    public void canAllAddressesBeFound(){
        assertEquals(2, entityManager().createQuery("SELECT a FROM Address a").getResultList().size());
    }

    @Test
    public void canAddressBeFoundOnId(){
        assertNotNull(entityManager().find(Address.class, 1L));
    }
}

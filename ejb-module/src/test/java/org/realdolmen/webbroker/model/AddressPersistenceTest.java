package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.model.user.User;

import javax.validation.ConstraintViolationException;

/**
 * Created by RDEAX37 on 5/10/2015.
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

    }

    @Test (expected = ConstraintViolationException.class)
    public void addressCantBePersistedWithoutNumber(){

    }

    @Test (expected = ConstraintViolationException.class)
    public void addressCantBePersistedWithoutPostalCode(){

    }

    @Test (expected = ConstraintViolationException.class)
    public void addressCantBePersistedWithoutCity(){

    }

    @Test
    public void canAllAddressesBeFound(){

    }

    @Test
    public void canAddressBeFoundOnId(){

    }
}

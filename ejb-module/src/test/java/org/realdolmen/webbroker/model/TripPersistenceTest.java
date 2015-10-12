package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.util.EntityFactory;

import javax.validation.ConstraintViolationException;

/**
 * Persistence test for the {@link Trip} entity.
 *
 * @author Youri Flement
 */
public class TripPersistenceTest extends DataSetPersistenceTest {

    private Trip trip;

    @Before
    public void setup() {
        trip = EntityFactory.createTrip();
    }

    @Test
    public void tripCanBePersisted() throws Exception {
        entityManager().persist(trip);
        assertNotNull(trip.getId());
    }

    @Test
    public void canFindAllTrips() throws Exception {
        assertEquals(2, entityManager().createQuery("select t from Trip t").getResultList().size());
    }

    @Test
    public void canFindTripById() throws Exception {
        assertNotNull(entityManager().find(Trip.class, 8000l));
    }

    @Test(expected = IllegalArgumentException.class)
    public void accommodationPriceIsPositive() throws Exception {
        trip.setAccommodationPrice(-100d);
    }

    @Test(expected = ConstraintViolationException.class)
    public void tripMustHaveAccommodationPrice() throws Exception {
        trip.setAccommodationPrice(null);
        entityManager().persist(trip);
    }

    @Test(expected = ConstraintViolationException.class)
    public void tripMustHaveFlight() throws Exception {
        trip.setFlight(null);
        entityManager().persist(trip);
    }

    @Test(expected = ConstraintViolationException.class)
    public void tripMustHaveTravelAgency() throws Exception {
        trip.setTravelAgency(null);
        entityManager().persist(trip);
    }

    @Test(expected = ConstraintViolationException.class)
    public void tripMustHaveStartDate() throws Exception {
        trip.setStartDate(null);
        entityManager().persist(trip);
    }

    @Test(expected = ConstraintViolationException.class)
    public void tripMustHaveEndDate() throws Exception {
        trip.setEndDate(null);
        entityManager().persist(trip);
    }
}

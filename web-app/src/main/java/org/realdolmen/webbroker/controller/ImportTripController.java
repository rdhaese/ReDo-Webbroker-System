package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.FlightXmlElement;
import org.realdolmen.webbroker.TripXmlElement;
import org.realdolmen.webbroker.TripsXmlElement;
import org.realdolmen.webbroker.model.Flight;
import org.realdolmen.webbroker.model.TravelAgency;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.repository.TripRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

@ManagedBean
@ViewScoped
public class ImportTripController {

    private Part file;

    @Inject
    TripRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    public void upload() {
        try {
            InputStream inputStream = file.getInputStream();
            JAXBContext context = JAXBContext.newInstance(TripsXmlElement.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            TripsXmlElement tripsXmlElement = (TripsXmlElement) unmarshaller.unmarshal(inputStream);

            for (TripXmlElement tripXmlElement : tripsXmlElement.getTrips()) {
                TravelAgency travelAgency = findTravelAgency(tripXmlElement);
                Flight flight = findFlight(tripXmlElement.getFlight());
                Trip trip = new Trip(flight, travelAgency, tripXmlElement.getAccommodationPrice(), tripXmlElement.getStartDate(), tripXmlElement.getEndDate());
                repository.add(trip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TravelAgency findTravelAgency(TripXmlElement tripXmlElement) {
        TypedQuery<TravelAgency> query =
                entityManager.createQuery("select t from TravelAgency t where t.name = :name", TravelAgency.class)
                              .setParameter("name", tripXmlElement.getTravelAgency());
        return query.getResultList().get(0);
    }

    private Flight findFlight(FlightXmlElement flight) {
        TypedQuery<Flight> query =
                entityManager.createQuery("select f from Flight f where f.company.name = :company and f.arrival.name = :arrival and f.departure.name = :departure and f.price = :price and f.availableSeats = :seats", Flight.class)
                                .setParameter("company", flight.getAirlineCompany())
                                .setParameter("departure", flight.getDepartureAirport())
                                .setParameter("seats", flight.getAvailableSeats())
                                .setParameter("price", flight.getPrice())
                                .setParameter("arrival", flight.getArrivalAirport());
        return query.getResultList().get(0);
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
}

package org.realdolmen.webbroker.listener;

import org.realdolmen.webbroker.exception.AmbiguousEntityException;
import org.realdolmen.webbroker.model.*;
import org.realdolmen.webbroker.model.user.AirlineCompanyEmployee;
import org.realdolmen.webbroker.model.user.ReDoAirEmployee;
import org.realdolmen.webbroker.model.user.TravelAgencyEmployee;
import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.repository.FlightRepository;
import org.realdolmen.webbroker.repository.TravelAgencyRepository;
import org.realdolmen.webbroker.repository.TripRepository;
import org.realdolmen.webbroker.xml.XmlSerializer;
import org.realdolmen.webbroker.xml.element.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet context listener which loads some initial data into the database.
 *
 * @author Youri Flement
 */
@WebListener
public class StartupListener implements ServletContextListener {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    XmlSerializer serializer;

    @Inject
    TravelAgencyRepository travelAgencyRepository;

    @Inject
    private FlightRepository flightRepository;

    @Inject
    private TripRepository tripRepository;

    @Override
    @Transactional
    public void contextInitialized(ServletContextEvent sce) {
        initializeRegions();
        initializeAirports();
        initializeUsers();
        initializeFlights();
        initializeTrips();
    }

    private void initializeTrips() {
        try {
            InputStream file = getClass().getClassLoader().getResourceAsStream("trips.xml");
            TripsXmlElement xmlElement = serializer.unmarshalStream(TripsXmlElement.class, file);
            for (TripXmlElement element : xmlElement.getTrips()) {
                processTripElement(element);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    protected void processTripElement(TripXmlElement tripElement) {
        try {
            TravelAgency agency = travelAgencyRepository.getSingleTravelAgency(tripElement.getTravelAgency());
            if (agency == null) {
            } else {
                FlightXmlElement flight1 = tripElement.getFlight();
                Flight flight = flightRepository.getSingleFlight(flight1.getAirlineCompany(), flight1.getDepartureAirport(), flight1.getArrivalAirport(), flight1.getPrice(), flight1.getAvailableSeats());
                if (flight == null) {
                } else {
                    Trip trip = new Trip(flight, agency, tripElement.getAccommodationPrice(), tripElement.getStartDate(), tripElement.getEndDate());
                    tripRepository.add(trip);
                }
            }
        } catch (AmbiguousEntityException e) {
            e.printStackTrace();
        }
    }

    // TODO: move these
    private void initializeFlights() {
        try {
            JAXBContext context = JAXBContext.newInstance(FlightsXmlElement.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream file = getClass().getClassLoader().getResourceAsStream("flights.xml");
            FlightsXmlElement flights = (FlightsXmlElement) unmarshaller.unmarshal(file);
            for (FlightXmlElement flightXmlElement : flights.getFlights()) {
                Flight flight = new Flight();
                flight.setArrival(airportMap.get(flightXmlElement.getArrivalAirport()));
                flight.setDeparture(airportMap.get(flightXmlElement.getDepartureAirport()));
                flight.setCompany(airlineCompanyMap.get(flightXmlElement.getAirlineCompany()));
                flight.setAvailableSeats(flightXmlElement.getAvailableSeats());
                flight.setPrice(flightXmlElement.getPrice());

                entityManager.persist(flight);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void initializeUsers() {
        airlineCompanyMap = new HashMap<>();
        try {
            JAXBContext context = JAXBContext.newInstance(UsersXmlElement.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream file = getClass().getClassLoader().getResourceAsStream("users.xml");
            UsersXmlElement users = (UsersXmlElement) unmarshaller.unmarshal(file);

            for (UserXmlElement userXmlElement : users.getUsers()) {
                User user;
                if("redoair".equalsIgnoreCase(userXmlElement.getRole())) {
                    user = new ReDoAirEmployee();
                } else if("travelagency".equalsIgnoreCase(userXmlElement.getRole())) {
                    user = new TravelAgencyEmployee();
                    TravelAgency agency = new TravelAgency(userXmlElement.getTravelagency());
                    entityManager.persist(agency);
                    ((TravelAgencyEmployee)user).setTravelAgency(agency);
                } else if("airlinecompany".equalsIgnoreCase(userXmlElement.getRole())) {
                    user = new AirlineCompanyEmployee();
                    AirlineCompany company = new AirlineCompany();
                    company.setName(userXmlElement.getAirlinecompany());
                    entityManager.persist(company);
                    airlineCompanyMap.put(company.getName(), company);
                    ((AirlineCompanyEmployee)user).setCompany(company);
                } else {
                    user = new User();
                }

                user.setUserName(userXmlElement.getUsername());
                user.setFirstName(userXmlElement.getFirstname());
                user.setLastName(userXmlElement.getLastname());
                user.setPassword(userXmlElement.getPassword());
                user.setSalt("no salt yet");
                entityManager.persist(user);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void initializeAirports() {
        airportMap = new HashMap<>();
        try {
            JAXBContext context = JAXBContext.newInstance(AirportsXmlElement.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream file = getClass().getClassLoader().getResourceAsStream("airports.xml");
            AirportsXmlElement airports = (AirportsXmlElement) unmarshaller.unmarshal(file);

            for (AirportXmlElement airportXmlElement : airports.getAirports()) {
                Airport airport = new Airport();
                airport.setName(airportXmlElement.getName());
                Region region = regionMap.get(airportXmlElement.getRegionCode());
                airport.setRegion(region);
                Address address = new Address();
                address.setCity(airportXmlElement.getCity());
                address.setCountry(airportXmlElement.getCountry());
                address.setNumber(airportXmlElement.getNumber());
                address.setPostalCode(airportXmlElement.getPostalCode());
                address.setStreet(airportXmlElement.getStreet());

                entityManager.persist(address);
                airport.setAddress(address);
                entityManager.persist(airport);
                airportMap.put(airport.getName(), airport);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Airport> airportMap;
    private Map<String, AirlineCompany> airlineCompanyMap;
    private Map<String, Region> regionMap;

    private void initializeRegions() {
        regionMap = new HashMap<>();
        try {
            JAXBContext context = JAXBContext.newInstance(RegionsXmlElement.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream file = getClass().getClassLoader().getResourceAsStream("regions.xml");
            RegionsXmlElement regions = (RegionsXmlElement) unmarshaller.unmarshal(file);

            for (RegionXmlElement regionXmlElement : regions.getRegions()) {
                Region region = new Region();
                region.setCode(regionXmlElement.getCode());
                region.setName(regionXmlElement.getName());
                entityManager.persist(region);
                regionMap.put(region.getCode(), region);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}

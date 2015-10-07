package org.realdolmen.webbroker.listener;

import org.realdolmen.webbroker.RegionXmlElement;
import org.realdolmen.webbroker.RegionsXmlElement;
import org.realdolmen.webbroker.UserXmlElement;
import org.realdolmen.webbroker.UsersXmlElement;
import org.realdolmen.webbroker.model.AirlineCompany;
import org.realdolmen.webbroker.model.Region;
import org.realdolmen.webbroker.model.TravelAgency;
import org.realdolmen.webbroker.model.user.AirlineCompanyEmployee;
import org.realdolmen.webbroker.model.user.ReDoAirEmployee;
import org.realdolmen.webbroker.model.user.TravelAgencyEmployee;
import org.realdolmen.webbroker.model.user.User;

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

/**
 * @author Youri Flement
 */
@WebListener
public class StartupListener implements ServletContextListener {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void contextInitialized(ServletContextEvent sce) {
        initializeRegions();
        initializeAirports();
        initializeUsers();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void initializeUsers() {
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

    }

    private void initializeRegions() {
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
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}

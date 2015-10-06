package org.realdolmen.webbroker.listener;

import org.realdolmen.webbroker.RegionXmlElement;
import org.realdolmen.webbroker.RegionsXmlElement;
import org.realdolmen.webbroker.model.Region;

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
        initializeAirportEmployees();
        initializeTravelEmployees();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void initializeTravelEmployees() {

    }

    private void initializeAirportEmployees() {

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

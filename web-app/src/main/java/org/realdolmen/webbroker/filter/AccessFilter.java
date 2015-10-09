package org.realdolmen.webbroker.filter;

import org.realdolmen.webbroker.controller.LoggedInUserController;
import org.realdolmen.webbroker.model.user.AirlineCompanyEmployee;
import org.realdolmen.webbroker.model.user.ReDoAirEmployee;
import org.realdolmen.webbroker.model.user.TravelAgencyEmployee;
import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.xml.XmlSerializer;
import org.realdolmen.webbroker.xml.element.PageXmlElement;
import org.realdolmen.webbroker.xml.element.PagesXmlElement;
import org.realdolmen.webbroker.xml.element.RoleXmlElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Primitive access filter which listens to all HTTP requests and redirects the visitor to the homepage if he
 * is not logged in and is trying to access a secure page. The list of secure pages is currently hardcoded.
 *
 * @author Youri Flement
 */
@WebFilter(urlPatterns = "/*")
public class AccessFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessFilter.class);

    Map<String, Collection<String>> accessMap;

    @Inject
    LoggedInUserController controller;

    @Inject
    XmlSerializer serializer;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        accessMap = new HashMap<>();

        InputStream file = getClass().getClassLoader().getResourceAsStream("access.xml");

        try {
            PagesXmlElement pagesXmlElement = serializer.unmarshalStream(PagesXmlElement.class, file);
            for (PageXmlElement pageXmlElement : pagesXmlElement.getTrips()) {
                List<String> roles = new ArrayList<>();
                for (RoleXmlElement roleXmlElement : pageXmlElement.getRoles().getRoles()) {
                    roles.add((roleXmlElement.getRole()));
                }
                accessMap.put(pageXmlElement.getPath(), roles);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServlet = (HttpServletRequest) request;
            String servletPath = httpServlet.getServletPath();
            User user = controller.getLoggedInUser();
            if(!hasPermission(user, servletPath)) {
                LOGGER.warn("Blocked access attempt at: " + servletPath + " by user: " + user);
                ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/index.faces");
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * Check whether the user has permission to access the page at the given servlet path.
     *
     * @param user  The user which wants to visit the page.
     * @param servletPath   The path the user is trying to access.
     * @return  <code>true</code> if the user has permission to access the page, <code>false</code> otherwise.
     */
    private boolean hasPermission(User user, String servletPath) {
        servletPath = servletPath.replace("/","");
        servletPath = servletPath.replace(".faces","");

        // allow access to style and images
        if(servletPath.contains("png") || servletPath.contains("css")) {
            return true;
        }

        Collection<String> allowedRoles = accessMap.get(servletPath);
        // block all access for paths we do not know
        if(allowedRoles == null) {
            return false;
        }

        // allow access to pages that everyone can access
        if(allowedRoles.contains("everyone")) {
            return true;
        }

        // check if the user role is allowed on this page
        String role = userToRole(user);
        return allowedRoles.contains(role);
    }

    private String userToRole(User user) {
        if (user instanceof AirlineCompanyEmployee) {
            return "airlinecompany";
        } else if (user instanceof TravelAgencyEmployee) {
            return "travelagency";
        } else if(user instanceof ReDoAirEmployee) {
            return "redoair";
        } else if(user == null) {
            return null;
        }

        return "user";
    }

    public void setAccessMap(Map<String, Collection<String>> accessMap) {
        this.accessMap = accessMap;
    }

    @Override
    public void destroy() {

    }
}

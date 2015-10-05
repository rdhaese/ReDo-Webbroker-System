package org.realdolmen.webbroker.filter;

import org.realdolmen.webbroker.controller.LoggedInUserController;
import org.realdolmen.webbroker.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@WebFilter(urlPatterns = "/*")
public class AccessFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessFilter.class);

    private Collection<String> securePages;

    @Inject
    LoggedInUserController controller;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        securePages = new ArrayList<>();
        securePages.add("book.faces");
        securePages.add("securePage.faces");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServlet = (HttpServletRequest) request;
            String servletPath = httpServlet.getServletPath();
            User user = controller.getLoggedInUser();
            if(!hasPermission(user, servletPath)) {
                LOGGER.warn("Blocked access attempt at: " + servletPath + " by user: " + user);
                // TODO: hardcoded URL...
                ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/index.faces");
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * Check whether the user has permission to access the page at the given servlet path.
     *
     * TODO: implement a more sophisticated permission system.
     *
     * @param user  The user which wants to visit the page.
     * @param servletPath   The path the user is trying to access.
     * @return  <code>true</code> if the user has permission to access the page, <code>false</code> otherwise.
     */
    private boolean hasPermission(User user, String servletPath) {
        servletPath = servletPath.replace("/","");
        if(securePages.contains(servletPath) && user == null) {
            return false;
        }

        return true;
    }

    @Override
    public void destroy() {

    }
}

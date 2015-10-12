package org.realdolmen.webbroker.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.realdolmen.webbroker.controller.LoggedInUserController;
import org.realdolmen.webbroker.model.user.User;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static org.mockito.Mockito.*;

/**
 * Mocking test for the {@link AccessFilter} to test whether it correctly blocks/allows access
 * attempts to secure and non-secure servlet paths.
 *
 * @author Youri Flement
 */
@RunWith(MockitoJUnitRunner.class)
public class AccessFilterTest {

    @Mock
    LoggedInUserController loggedInUserController;

    @InjectMocks
    AccessFilter filter;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    FilterChain chain;

    @Before
    public void setup() {
        Map<String, Collection<String>> pages = new HashMap<>();
        pages.put("homepage", Arrays.asList("everyone"));
        pages.put("needtologin", Arrays.asList("user", "admin"));
        pages.put("none", new ArrayList<>());
        filter.setAccessMap(pages);
    }

    @Test
    public void accessIsBlockIfPageNotKnown() throws Exception {
        when(request.getServletPath()).thenReturn("/unknown.faces");
        filter.doFilter(request, response, chain);
        when(request.getServletPath()).thenReturn("garbage input");
        filter.doFilter(request, response, chain);

        verify(response, times(2)).sendRedirect(Mockito.anyString().concat("/index.faces"));
    }

    @Test
    public void everyoneCanAccessPageForEveryone() throws Exception {
        when(request.getServletPath()).thenReturn("/homepage.faces");
        filter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
    }

    @Test
    public void cannotAccessSecurePagesWithoutLogin() throws Exception {
        when(request.getServletPath()).thenReturn("/needtologin.faces");
        when(loggedInUserController.getLoggedInUser()).thenReturn(null);

        filter.doFilter(request, response, chain);

        verify(response, times(1)).sendRedirect(Mockito.anyString().concat("/index.faces"));
    }

    @Test
    public void canAccessSecurePageWithLogin() throws Exception {
        when(request.getServletPath()).thenReturn("/needtologin.faces");
        when(loggedInUserController.getLoggedInUser()).thenReturn(new User());

        filter.doFilter(request, response, chain);
        verify(chain).doFilter(request, response);
    }

    @Test
    public void canAccessStaticResources() throws Exception {
        when(loggedInUserController.getLoggedInUser()).thenReturn(new User());
        when(request.getServletPath()).thenReturn("css.faces");
        filter.doFilter(request, response, chain);

        when(request.getServletPath()).thenReturn("image.png");
        filter.doFilter(request, response, chain);

        verify(chain, times(2)).doFilter(request, response);
    }
}

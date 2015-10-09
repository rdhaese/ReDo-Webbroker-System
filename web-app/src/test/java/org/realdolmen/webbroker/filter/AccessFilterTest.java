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
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        Collection<String> securePages = new ArrayList<>();
        securePages.add("booking");
//        filter.setSecurePages(securePages);
    }

    @Test
    public void filterHasNoEffectIfNoPermissionRequired() throws Exception {
        // Attempt with no user and no secure page
        when(request.getServletPath()).thenReturn("some-page.faces");
        when(loggedInUserController.getLoggedInUser()).thenReturn(null);
        filter.doFilter(request, response, chain);

        // Attempt with user and no secure page
        when(request.getServletPath()).thenReturn("some-page.faces");
        when(loggedInUserController.getLoggedInUser()).thenReturn(new User());
        filter.doFilter(request, response, chain);

        // Should continue the chain in both cases
        verify(chain, times(2)).doFilter(request, response);
    }

    @Test
    public void filterBlocksSecurePageForUnauthorizedUser() throws Exception {
        when(request.getServletPath()).thenReturn("booking");
        when(loggedInUserController.getLoggedInUser()).thenReturn(null);
        filter.doFilter(request, response, chain);

        verify(response).sendRedirect(Mockito.anyString().concat("/index.faces"));
    }

    @Test
    public void filterAllowsSecurePageForAuthorizedUser() throws Exception {
        when(request.getServletPath()).thenReturn("booking");
        when(loggedInUserController.getLoggedInUser()).thenReturn(new User());
        filter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
    }
}

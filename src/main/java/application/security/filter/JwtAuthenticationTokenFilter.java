package application.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * Implementation of {@link javax.servlet.Filter} which handle requests with jwt token within their header.
 *
 * Created by matan,
 * On 04/10/2016.
 */
public class JwtAuthenticationTokenFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    private static final String BEARER_HEADER_PREFIX = "bearer";

    private String tokenHeader;

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationTokenFilter(String tokenHeader, AuthenticationManager authenticationManager) {
        this.tokenHeader = tokenHeader;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Optional<String> authHeader = Optional.ofNullable(httpRequest.getHeader(this.tokenHeader));

        if (authHeader.isPresent() && authHeader.get().toLowerCase().startsWith(BEARER_HEADER_PREFIX)) {
            String authToken = authHeader.get().substring(BEARER_HEADER_PREFIX.length()).trim();
            processTokenAuthentication(authToken);
        }

        chain.doFilter(request, response);
    }

    private void processTokenAuthentication(String token) {
        logger.debug("JWT filter is processing a token");
        Authentication resultOfAuthentication = tryToAuthenticateWithToken(token);
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
    }

    private Authentication tryToAuthenticateWithToken(String token) {
        PreAuthenticatedAuthenticationToken requestAuthentication = new PreAuthenticatedAuthenticationToken(token, null);
        Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new InternalAuthenticationServiceException("Unable to authenticate Domain User for provided credentials");
        }
        logger.debug("JWT filter successfully authenticated a user");
        return responseAuthentication;
    }

}

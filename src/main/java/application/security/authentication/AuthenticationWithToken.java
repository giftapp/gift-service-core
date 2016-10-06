package application.security.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;

/**
 * {@link PreAuthenticatedAuthenticationToken} implementation for
 * token authentication authentication.
 *
 * @author Matan Lachmish
 * @date 06/10/2016
 */
public class AuthenticationWithToken extends PreAuthenticatedAuthenticationToken {
    public AuthenticationWithToken(Object aPrincipal, Object aCredentials) {
        super(aPrincipal, aCredentials);
    }

    public AuthenticationWithToken(Object aPrincipal, Object aCredentials, Collection<? extends GrantedAuthority> anAuthorities) {
        super(aPrincipal, aCredentials, anAuthorities);
    }

    public void setAccessToken(String token) {
        setDetails(token);
    }

    public String getAccessToken() {
        return (String)getDetails();
    }
}

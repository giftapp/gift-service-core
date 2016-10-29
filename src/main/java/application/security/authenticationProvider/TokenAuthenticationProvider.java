package application.security.authenticationProvider;

import application.model.AuthorityName;
import application.security.authentication.AuthenticationWithToken;
import application.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * AuthenticationProvider implementation for {@link PreAuthenticatedAuthenticationToken} authentication.
 * This class will authenticate a user that logs in with an access token.
 *
 * Created by matan,
 * On 05/10/2016.
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional optionalToken = Optional.ofNullable(authentication.getPrincipal());
        if (!optionalToken.isPresent()) {
            throw new BadCredentialsException("Invalid token");
        }

        String accessToken = (String) optionalToken.get();

        if (!jwtTokenUtil.validateToken(accessToken)) {
            throw new BadCredentialsException("Invalid token or token expired");
        }

        //Return AuthenticationWithToken
        AuthenticationWithToken authenticationWithToken = new AuthenticationWithToken(jwtTokenUtil.getUserIdFromToken(accessToken), null, AuthorityName.AuthorityNameEnum.getGrantedAuthority(AuthorityName.AuthorityNameEnum.USER));
        authenticationWithToken.setAccessToken(accessToken);
        return authenticationWithToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}

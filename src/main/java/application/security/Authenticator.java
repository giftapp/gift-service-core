package application.security;

import application.facebook.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by matan on 20/05/2016.
 */

@Component
public class Authenticator {

    private static final Logger log = Logger.getLogger( Authenticator.class.getName() );

    @Autowired
    private FacebookService facebookService;

    public boolean verifyAuthentication(String userAccessToken) {
        return facebookService.validateUserAccessToken(userAccessToken);
    }
}
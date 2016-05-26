package application.restControllers;

import application.model.User;
import application.restControllers.exceptions.UnauthorizedUserException;
import application.security.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Created by matan on 22/05/2016.
 */

@Controller
public class AuthorizedControllerBase {

    @Autowired
    private Authenticator authenticator;

    @ModelAttribute("currentUser")
    public User authenticateUser(@RequestHeader(value="api_key") String accessToken) {
        User user = authenticator.verifyAuthentication(accessToken);
        if (user == null) {
            throw new UnauthorizedUserException();
        }
        return user;
    }

}

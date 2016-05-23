package application.restControllers;

import application.model.User;
import application.repositories.user.UserRepository;
import application.restControllers.exceptions.ObjectNotFoundException;
import application.restControllers.exceptions.UnauthorizedUserException;
import application.security.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @ModelAttribute("currentUser")
    public User authenticateUser(@RequestHeader(value="api_key") String accessToken) {
        if (!authenticator.verifyAuthentication(accessToken)) {
            throw new UnauthorizedUserException();
        }
        return userRepository.findByAccessToken(accessToken).orElseThrow(() -> new ObjectNotFoundException(this.getClass().getName(), accessToken));
    }

}

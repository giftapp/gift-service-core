package application.restControllers;

import application.facebook.FacebookService;
import application.model.User;
import application.repositories.user.UserRepository;
import application.restControllers.exceptions.ObjectAlreadyExistEcxeption;
import application.restControllers.exceptions.UnauthorizedUserException;
import application.security.Authenticator;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

/**
 * Created by matan on 22/05/2016.
 */

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private static final Logger log = Logger.getLogger( UserController.class.getName() );

    @Autowired
    private Authenticator authenticator;

    @Autowired
    private FacebookService facebookService;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    //POST
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User registerUser(@Valid @RequestBody RegisterUserResponse registerUserResponse) {

        String userAccessToken = registerUserResponse.getAccessToken();

        if (!authenticator.verifyAuthentication(userAccessToken)) {
            throw new UnauthorizedUserException();
        }
        if (userRepository.findByAccessToken(userAccessToken).isPresent()) {
            throw new ObjectAlreadyExistEcxeption(User.class.getName(), userAccessToken);
        }

        User user = facebookService.getUserFromToken(userAccessToken);
        userRepository.save(user);
        return user;
    }

    private static final class RegisterUserResponse {
        @NotNull
        private String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        @JsonCreator
        public RegisterUserResponse(@JsonProperty("accessToken") String accessToken) {
            this.accessToken = accessToken;
        }
    }
}



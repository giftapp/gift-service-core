package application.restControllers;

import application.model.User;
import application.repositories.user.UserRepository;
import application.restControllers.exceptions.InvalidObjectIdException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by matan on 10/05/2016.
 */

@RestController
@RequestMapping("/user/{userId}")
public class UserController {

    private static final Logger log = Logger.getLogger( UserController.class.getName() );

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public User user(@PathVariable String userId) {
        return this.validateUser(userId);
    }

    private User validateUser(String userId) {
        try {
            ObjectId id = new ObjectId(userId);
            return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(userId));
        } catch (IllegalArgumentException e) {
            log.log(Level.WARNING, "Unable to parse ObjectId from: " + userId);
            throw new InvalidObjectIdException(userId);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class UserNotFoundException extends RuntimeException {

        public UserNotFoundException(String userId) {
            super("could not find user '" + userId + "'.");
        }

    }
}

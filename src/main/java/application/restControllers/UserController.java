package application.restControllers;

import application.model.Event;
import application.model.Gift;
import application.model.User;
import application.repositories.event.EventRepository;
import application.repositories.gift.GiftRepository;
import application.repositories.user.UserRepository;
import application.restControllers.exceptions.InvalidObjectIdException;
import application.restControllers.exceptions.ObjectNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by matan on 10/05/2016.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = Logger.getLogger( UserController.class.getName() );

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GiftRepository giftRepository;

    //REST ENDPOINTS

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(path = "/{userId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable String userId) {
        return this.validateUser(userId);
    }

    @RequestMapping(path = "/{userId}/event" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getUserEvents(@PathVariable String userId) {
        User user = this.validateUser(userId);
        return eventRepository.eventsForUser(user.getId());
    }

    @RequestMapping(path = "/{userId}/gift" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Gift> getUserGifts(@PathVariable String userId) {
        User user = this.validateUser(userId);
        return giftRepository.giftsForUser(user.getId());
    }

    //Utils
    private User validateUser(String userId) {
        try {
            ObjectId id = new ObjectId(userId);
            return this.userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(this.getClass().getName(), userId));
        } catch (IllegalArgumentException e) {
            log.log(Level.WARNING, "Unable to parse ObjectId from: " + userId);
            throw new InvalidObjectIdException(userId);
        }
    }
}

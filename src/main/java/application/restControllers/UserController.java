package application.restControllers;

import application.model.Event;
import application.model.Gift;
import application.model.User;
import application.repositories.event.EventRepository;
import application.repositories.gift.GiftRepository;
import application.repositories.user.UserRepository;
import application.repositories.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by matan on 10/05/2016.
 */

@RestController
@RequestMapping("/user")
public class UserController extends AuthorizedControllerBase {

    private static final Logger log = Logger.getLogger( UserController.class.getName() );

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private RepositoryUtils repositoryUtils;

    //REST ENDPOINTS

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(path = "/{userId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable String userId) {
        return this.repositoryUtils.validateObjectExist(User.class, userId);
    }

    @RequestMapping(path = "/{userId}/event" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getUserEvents(@PathVariable String userId) {
        User user = repositoryUtils.validateObjectExist(User.class, userId);
        return eventRepository.eventsForUser(user.getId());
    }

    @RequestMapping(path = "/{userId}/gift" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Gift> getUserGifts(@ModelAttribute("currentUser") User currentUser, @PathVariable String userId) {
        User user = repositoryUtils.validateObjectExist(User.class, userId);
        return giftRepository.giftsForUser(user.getId());
    }
}

package application.restControllers;

import application.facebook.FacebookService;
import application.model.Event;
import application.model.Gift;
import application.model.User;
import application.outbound.email.EmailService;
import application.repositories.event.EventRepository;
import application.repositories.gift.GiftRepository;
import application.repositories.user.UserRepository;
import application.repositories.utils.RepositoryUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by matan on 10/05/2016.
 */

@RestController
@RequestMapping("/user")
public class UserController extends AuthorizedControllerBase {

    private static final Logger log = Logger.getLogger( UserController.class.getName() );

    private static final String PATH_SHORTCUT_ME = "me";

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private RepositoryUtils repositoryUtils;

    @Autowired
    private EmailService emailService;

    @Autowired
    private FacebookService facebookService;

    //REST ENDPOINTS

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(path = "/{userId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@ModelAttribute("currentUser") User currentUser, @PathVariable String userId) {
        if (userId.equals(PATH_SHORTCUT_ME)) {
            return currentUser;
        }
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

    //PUT
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@ModelAttribute("currentUser") User currentUser, @Valid @RequestBody UpdateUserRequestBody updateUserRequestBody) {
        if (updateUserRequestBody.email != null && !currentUser.getEmail().equals(updateUserRequestBody.email)) {
            //Send Welcome email
            emailService.sendWelcomeMessage(updateUserRequestBody.email);
        }

        currentUser.setFirstName(updateUserRequestBody.firstName);
        currentUser.setLastName(updateUserRequestBody.lastName);
        currentUser.setEmail(updateUserRequestBody.email);
        currentUser.setPhoneNumber(updateUserRequestBody.phoneNumber);
        currentUser.setAvatarURL(updateUserRequestBody.avatarURL);

        return userRepository.save(currentUser);
    }

    private static final class UpdateUserRequestBody {
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String avatarURL;

        @JsonCreator
        public UpdateUserRequestBody(@JsonProperty("firstName")String firstName,
                                     @JsonProperty("lastName")String lastName,
                                     @JsonProperty("email")String email,
                                     @JsonProperty("phoneNumber")String phoneNumber,
                                     @JsonProperty("avatarURL")String avatarURL) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.avatarURL = avatarURL;
        }
    }

    //PUT
    @RequestMapping(path = "facebook",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User setFacebookAccount(@ModelAttribute("currentUser") User currentUser, @Valid @RequestBody SetFaceBookAccountRequestBody setFaceBookAccountRequestBody) {
        String originalEmail = currentUser.getEmail();
        currentUser = facebookService.updateUserFromToken(currentUser,setFaceBookAccountRequestBody.facebookAccessToken);

        if (currentUser.getEmail() != null && !currentUser.getEmail().equals(originalEmail)) {
            //Send Welcome email
            emailService.sendWelcomeMessage(currentUser.getEmail());
        }

        return userRepository.save(currentUser);
    }

    private static final class SetFaceBookAccountRequestBody {
        @NotNull
        private String facebookAccessToken;

        @JsonCreator
        public SetFaceBookAccountRequestBody(@JsonProperty("facebookAccessToken")String facebookAccessToken) {
            this.facebookAccessToken = facebookAccessToken;
        }
    }
}

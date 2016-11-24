package application.restAPI.controllers.User;

import application.facebook.FacebookService;
import application.model.User;
import application.outbound.email.EmailService;
import application.repositories.event.EventRepository;
import application.repositories.gift.GiftRepository;
import application.repositories.user.UserRepository;
import application.repositories.utils.RepositoryUtils;
import application.restAPI.controllers.User.dto.request.SetFaceBookAccountRequestDTO;
import application.restAPI.controllers.User.dto.request.UpdateUserRequestDTO;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by matan on 10/05/2016.
 */

@RestController
public class UserControllerImpl implements UserControllerAPI {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);

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
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = Lists.newArrayList(userRepository.findAll());
        return ResponseEntity.ok(userList);
    }

    @RequestMapping(path = "/{userId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@AuthenticationPrincipal String loggedInUserId, @PathVariable String userId) {
        if (userId.equals(PATH_SHORTCUT_ME)) {
            User loggedInUser = repositoryUtils.validateObjectExist(User.class, loggedInUserId);
            return ResponseEntity.ok(loggedInUser);
        }
        User user = this.repositoryUtils.validateObjectExist(User.class, userId);
        return ResponseEntity.ok(user);
    }

//    @RequestMapping(path = "/{userId}/event" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Event> getUserEvents(@PathVariable String userId) {
//        User user = repositoryUtils.validateObjectExist(User.class, userId);
//        return eventRepository.eventsForUser(user.getId());
//    }
//
//    @RequestMapping(path = "/{userId}/gift" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Gift> getUserGifts(@ModelAttribute("currentUser") User currentUser, @PathVariable String userId) {
//        User user = repositoryUtils.validateObjectExist(User.class, userId);
//        return giftRepository.giftsForUser(user.getId());
//    }

    //PUT
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@AuthenticationPrincipal String loggedInUserId, @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        User loggedInUser = repositoryUtils.validateObjectExist(User.class, loggedInUserId);

        sendWelcomeEmailIfNeeded(updateUserRequestDTO.getEmail(), loggedInUser.getEmail());

        loggedInUser.setFirstName(updateUserRequestDTO.getFirstName());
        loggedInUser.setLastName(updateUserRequestDTO.getLastName());
        loggedInUser.setEmail(updateUserRequestDTO.getEmail());
        loggedInUser.setPhoneNumber(updateUserRequestDTO.getPhoneNumber());
        loggedInUser.setAvatarURL(updateUserRequestDTO.getAvatarURL());

        loggedInUser.setNeedsEdit(false);
        userRepository.save(loggedInUser);
        return ResponseEntity.ok(loggedInUser);
    }

    //POST
    @RequestMapping(path = "/facebook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> setFacebookAccount(@AuthenticationPrincipal String loggedInUserId, @Valid @RequestBody SetFaceBookAccountRequestDTO setFaceBookAccountRequestDTO) {
        User loggedInUser = repositoryUtils.validateObjectExist(User.class, loggedInUserId);
        String originalEmail = loggedInUser.getEmail();
        loggedInUser = facebookService.updateUserFromToken(loggedInUser,setFaceBookAccountRequestDTO.getFacebookAccessToken());
        loggedInUser.setNeedsEdit(false);

        sendWelcomeEmailIfNeeded(loggedInUser.getEmail(), originalEmail);

        userRepository.save(loggedInUser);
        return ResponseEntity.ok(loggedInUser);
    }

    //Private
    private void sendWelcomeEmailIfNeeded(String newEmail, String oldEmail) {
        if (newEmail != null && (oldEmail == null || !oldEmail.equals(newEmail))) {
            //Send Welcome email
            emailService.sendWelcomeMessage(newEmail);
        }
    }
}

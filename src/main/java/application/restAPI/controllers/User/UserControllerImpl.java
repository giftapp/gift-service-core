package application.restAPI.controllers.User;

import application.model.User;
import application.restAPI.controllers.User.dto.request.SetFaceBookAccountRequestDTO;
import application.restAPI.controllers.User.dto.request.UpdateUserRequestDTO;
import application.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;

    //REST ENDPOINTS

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @RequestMapping(path = "/{userId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@AuthenticationPrincipal String loggedInUserId, @PathVariable String userId) {
        if (userId.equals(PATH_SHORTCUT_ME)) {
            User loggedInUser = userService.getUser(loggedInUserId);
            return ResponseEntity.ok(loggedInUser);
        }
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //PUT
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@AuthenticationPrincipal String loggedInUserId, @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        User updatedUser = userService.updateUser(
                loggedInUserId,
                updateUserRequestDTO.getFirstName(),
                updateUserRequestDTO.getLastName(),
                updateUserRequestDTO.getEmail(),
                updateUserRequestDTO.getAvatarURL()
        );
        return ResponseEntity.ok(updatedUser);
    }

    //POST
    @RequestMapping(path = "/facebook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> setFacebookAccount(@AuthenticationPrincipal String loggedInUserId, @Valid @RequestBody SetFaceBookAccountRequestDTO setFaceBookAccountRequestDTO) {
        User updatedUser = userService.setFacebookAccount(loggedInUserId, setFaceBookAccountRequestDTO.getFacebookAccessToken());
        return ResponseEntity.ok(updatedUser);
    }

}

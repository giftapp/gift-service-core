package application.restAPI.controllers.User;

import application.model.AuthorityName;
import application.model.User;
import application.restAPI.controllers.User.dto.request.SetFaceBookAccountRequestDTO;
import application.restAPI.controllers.User.dto.request.UpdateUserRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Matan Lachmish,
 * On 23/10/2016.
 */
@Api(
        description = "Users API",
        consumes = "application/json",
        produces = "application/json")
@RequestMapping("/user")
@Secured(AuthorityName.ROLE_USER_STRING)
public interface UserControllerAPI {

    //GET
//    @ApiOperation(
//            value = "Get all users",
//            notes = "List all existing users")
//    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @Secured(AuthorityName.ROLE_ADMIN_STRING)
//    ResponseEntity<List<User>> getAllUsers();

    @ApiOperation(
            value = "Get user",
            notes = "Get a user with a specific id," +
                    "Use 'me' to get the logged in user.")
    @RequestMapping(path = "/{userId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getUser(@AuthenticationPrincipal String loggedInUserId,
                           @ApiParam(name = "userId", required = true, example = "me") @PathVariable(value = "userId") String userId);

    //PUT
    @ApiOperation(
            value = "Update user details",
            notes = "Use when first registering a user or when editig user's profile.")
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> updateUser(@AuthenticationPrincipal String loggedInUserId,
                              @ApiParam(name = "UpdateUserRequest", required = true) @Valid @NotNull @RequestBody UpdateUserRequestDTO updateUserRequestDTO);

    //POST
    @ApiOperation(
            value = "Associate a facebook account with a user account",
            notes = "User details will be overridden with facebook information")
    @RequestMapping(path = "/facebook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> setFacebookAccount(@AuthenticationPrincipal String loggedInUserId, @Valid @RequestBody SetFaceBookAccountRequestDTO setFaceBookAccountRequestDTO);

}

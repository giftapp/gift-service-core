package application.restAPI.controllers.Toast;

import application.model.AuthorityName;
import application.model.Toast;
import application.restAPI.controllers.Toast.dto.CreateToastRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by matan,
 * On 04/01/2017.
 */

@Api(
        description = "Toast API",
        consumes = "application/json",
        produces = "application/json")
@RequestMapping("/toast")
@Secured(AuthorityName.ROLE_USER_STRING)
public interface ToastControllerAPI {

    //GET
    @ApiOperation(
            value = "Get toast",
            notes = "Get a toast with a specific id")
    @RequestMapping(path = "/{toastId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Toast> getToast(@PathVariable("toastId") String toastId);

    //POST
    @ApiOperation(value = "Create a new toast")
    @RequestMapping(path = "/me", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Toast> createToast(@AuthenticationPrincipal String loggedInUserId, @Valid @RequestBody CreateToastRequestDTO createToastRequestDTO);

}

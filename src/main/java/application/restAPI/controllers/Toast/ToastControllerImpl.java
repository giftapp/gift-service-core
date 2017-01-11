package application.restAPI.controllers.Toast;

import application.model.Toast;
import application.restAPI.controllers.Toast.dto.CreateToastRequestDTO;
import application.services.ToastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by matan,
 * On 04/01/2017.
 */

@RestController
public class ToastControllerImpl implements ToastControllerAPI {

    private static final Logger logger = LoggerFactory.getLogger(ToastControllerImpl.class);

    @Autowired
    private ToastService toastService;

    //GET
    @Override
    @RequestMapping(path = "/{toastId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Toast> getToast(@PathVariable("toastId") String toastId) {
        return ResponseEntity.ok(toastService.getToast(toastId));
    }

    //POST
    @RequestMapping(path = "/me", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Toast> createToast(@AuthenticationPrincipal String loggedInUserId, @Valid @RequestBody CreateToastRequestDTO createToastRequestDTO) {
        return ResponseEntity.ok(toastService.createToast(
                loggedInUserId,
                createToastRequestDTO.getEventId(),
                createToastRequestDTO.getToastFlavor(),
                createToastRequestDTO.getGiftPresenters(),
                createToastRequestDTO.getVideoUrl(),
                createToastRequestDTO.getImageUrl(),
                createToastRequestDTO.getText()
                )
        );
    }

}

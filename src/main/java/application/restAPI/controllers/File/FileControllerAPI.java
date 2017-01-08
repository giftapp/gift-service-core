package application.restAPI.controllers.File;

import application.model.AuthorityName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by matan,
 * On 06/01/2017.
 */

@Api(
        description = "File API",
        consumes = "application/json",
        produces = "application/json")
@RequestMapping("/file")
@Secured(AuthorityName.ROLE_USER_STRING)
public interface FileControllerAPI {

    //POST
    @ApiOperation(
            value = "Upload image file",
            notes = "Upload image file and get back image URI")
    @RequestMapping(path = "/image" ,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity uploadImage(@RequestParam("file") MultipartFile file);

    @ApiOperation(
            value = "Upload video file",
            notes = "Upload video file and get back video URI")
    @RequestMapping(path = "/video" ,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity uploadVideo(@RequestParam("file") MultipartFile file);

}

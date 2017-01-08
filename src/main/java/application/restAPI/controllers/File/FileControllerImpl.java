package application.restAPI.controllers.File;

import application.file.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

/**
 * Created by matan,
 * On 06/01/2017.
 */

@RestController
public class FileControllerImpl implements FileControllerAPI {

    private static final Logger logger = LoggerFactory.getLogger(FileControllerImpl.class);

    @Autowired
    private FileService fileService;

    //POST
    @Override
    @RequestMapping(path = "/image" ,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String resourceURI = fileService.uploadImage(file.getBytes());
            return ResponseEntity.created(URI.create(resourceURI)).build();
        } catch (IOException e) {
            logger.error("Failed to upload image: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @Override
    @RequestMapping(path = "/video" ,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadVideo(@RequestParam("file") MultipartFile file) {
        try {
            String resourceURI = fileService.uploadVideo(file.getBytes());
            return ResponseEntity.created(URI.create(resourceURI)).build();
        } catch (IOException e) {
            logger.error("Failed to upload video: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload video");
        }
    }

}
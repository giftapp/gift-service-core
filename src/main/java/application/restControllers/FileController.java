package application.restControllers;

import application.repositories.file.StorageFileNotFoundException;
import application.repositories.file.StorageService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController extends AuthorizedControllerBase {

    @Autowired
    private StorageService storageService;

    //REST ENDPOINTS

    //GET
    @RequestMapping(path = "/{filename:.+}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    //POST
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UploadFileResponse> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String path = storageService.store(file);
        UploadFileResponse uploadFileResponse = new UploadFileResponse(path);
        return ResponseEntity
                .ok()
                .body(uploadFileResponse);
    }

    private static final class UploadFileResponse {
        public String filePath;

        @JsonCreator
        public UploadFileResponse(@JsonProperty("filePath")String filePath) {
            this.filePath = filePath;
        }
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
package application.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by matan,
 * On 06/01/2017.
 */

@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private CloudinaryClient cloudinaryClient;

    public String uploadImage(byte[] file) throws IOException {
        Map response = cloudinaryClient.uploadImage(file);
        return (String) response.get("secure_url");
    }

    public String uploadVideo(byte[] file) throws IOException {
        Map response = cloudinaryClient.uploadVideo(file);
        return (String) response.get("secure_url");
    }
}

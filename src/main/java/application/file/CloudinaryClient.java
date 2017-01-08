package application.file;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by matan,
 * On 06/01/2017.
 */

@Component
public class CloudinaryClient {

    private static final Logger logger = LoggerFactory.getLogger(CloudinaryClient.class);

    private Cloudinary cloudinary;

    @Autowired
    public CloudinaryClient(@Value("${cloudinary.cloud-name}") String cloudName, @Value("${cloudinary.api-key}") String apiKey, @Value("${cloudinary.api-secret}") String apiSecret) {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    public Map uploadImage(byte[] file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        return uploadResult;
    }

    public Map uploadVideo(byte[] file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.asMap("resource_type", "video"));
        return uploadResult;
    }
}

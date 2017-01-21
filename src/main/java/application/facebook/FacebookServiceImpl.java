package application.facebook;

import application.model.User;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by matan on 22/05/2016.
 */

@Component
public class FacebookServiceImpl implements FacebookService {

    private static final Logger logger = LoggerFactory.getLogger(FacebookServiceImpl.class);

    private static final String FB_APP_ID = "801889286579558";
    private static final String FB_APP_SECRET = "d4c25a68db5c6dbab8bcaca7bfe57e6e";

    private DefaultFacebookClient appFacebookClient;
    private FacebookClient.AccessToken appAccessToken;


    public FacebookServiceImpl() {
        this.appAccessToken = new DefaultFacebookClient(Version.VERSION_2_6).obtainAppAccessToken(FB_APP_ID, FB_APP_SECRET);
        appFacebookClient = new DefaultFacebookClient(this.appAccessToken.getAccessToken(), Version.VERSION_2_6);
    }

    @Override
    public boolean validateUserAccessToken(String userAccessToken) {
        try {
            FacebookClient.DebugTokenInfo tokenInfo = appFacebookClient.debugToken(userAccessToken);
            return tokenInfo.isValid();
        } catch (FacebookException e) {
            logger.error("Error while validating facebook token" + e);
            return false;
        }
    }

    @Override
    public User updateUserFromToken(User user, String userFacebookAccessToken) {
        try {
            DefaultFacebookClient userFacebookClient = new DefaultFacebookClient(userFacebookAccessToken, Version.VERSION_2_6);
            com.restfb.types.User fbUser = userFacebookClient.fetchObject("me", com.restfb.types.User.class, Parameter.with("fields", "id,first_name,last_name,email,picture.width(160).height(160)"));
            user.setFirstName(fbUser.getFirstName());
            user.setLastName(fbUser.getLastName());
            user.setEmail(fbUser.getEmail());
            user.setAvatarURL(fbUser.getPicture().getUrl());
            user.setFacebookAccessToken(userFacebookAccessToken);
            logger.debug("Updated user from facebook API successfully");
            return user;
        } catch (FacebookException e) {
            logger.error("Failed updating user from facebook API" + e);
            return null;
        }
    }
}

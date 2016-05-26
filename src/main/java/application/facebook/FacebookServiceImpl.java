package application.facebook;

import application.model.User;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by matan on 22/05/2016.
 */

@Component
public class FacebookServiceImpl implements FacebookService {

    private static final Logger log = Logger.getLogger( FacebookServiceImpl.class.getName() );

    private static final String FB_APP_ID = "223077214742059";
    private static final String FB_APP_SECRET = "63f22cc75e015205555ffb925a882c09";

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
            return false;
        }
    }

    @Override
    public User updateUserFromToken(User user, String userFacebookAccessToken) {
        try {
            DefaultFacebookClient userFacebookClient = new DefaultFacebookClient(userFacebookAccessToken, Version.VERSION_2_6);
            com.restfb.types.User fbUser = userFacebookClient.fetchObject("me", com.restfb.types.User.class, Parameter.with("fields", "id,first_name,last_name,email,picture"));
            user.setFirstName(fbUser.getFirstName());
            user.setLastName(fbUser.getLastName());
            user.setEmail(fbUser.getEmail());
            user.setAvatarURL(fbUser.getPicture().getUrl());
            user.setFacebookAccessToken(userFacebookAccessToken);
            return user;
        } catch (FacebookException e) {
            log.log(Level.SEVERE, "Failed updating user from facebook API" + e);
            return null;
        }
    }
}
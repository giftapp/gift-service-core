package application.security;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.FacebookClient.DebugTokenInfo;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by matan on 20/05/2016.
 */

@Component
public class Authenticator {

    private static final Logger log = Logger.getLogger( Authenticator.class.getName() );
    private static final String FB_APP_ID = "223077214742059";
    private static final String FB_APP_SECRET = "63f22cc75e015205555ffb925a882c09";
//    private static final String FB_CLIENT_TOKEN = "e0e973745f7a67298b2f745ad778f3a1";

    private DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.VERSION_2_6);
    private AccessToken accessToken;


    public Authenticator() {
        this.accessToken = facebookClient.obtainAppAccessToken(FB_APP_ID, FB_APP_SECRET);
        facebookClient = new DefaultFacebookClient(this.accessToken.getAccessToken(), Version.VERSION_2_6);
//        log.log(Level.INFO, "My application access token: " + accessToken);
    }

    public boolean verifyAuthentication(String userAccessToken) {
        try {
            DebugTokenInfo tokenInfo = facebookClient.debugToken(userAccessToken);
            log.log(Level.INFO, tokenInfo.toString());
        } catch (FacebookException e) {
            return false;
        }
        return true;
    }
}
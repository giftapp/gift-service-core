package application.facebook;

import application.model.User;

/**
 * Created by matan on 22/05/2016.
 */
public interface FacebookService {

    boolean validateUserAccessToken(String userAccessToken);

    User updateUserFromToken(User user, String userFacebookAccessToken);

}

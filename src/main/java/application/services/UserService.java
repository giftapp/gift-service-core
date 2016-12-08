package application.services;

import application.facebook.FacebookService;
import application.model.User;
import application.outbound.email.EmailService;
import application.repositories.user.UserRepository;
import application.repositories.utils.RepositoryUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by matan,
 * On 08/12/2016.
 */

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    private RepositoryUtils repositoryUtils;

    @Autowired
    private EmailService emailService;

    @Autowired
    private FacebookService facebookService;

    public List<User> getAllUsers() {
        List<User> userList = Lists.newArrayList(userRepository.findAll());
        return userList;
    }

    public User getUser(String userId) {
        User user = this.repositoryUtils.validateObjectExist(User.class, userId);
        return user;
    }

    public User updateUser(String userId, String firstName, String lastName, String email, String avatarUrl) {
        User userToEdit = repositoryUtils.validateObjectExist(User.class, userId);

        sendWelcomeEmailIfNeeded(email, userToEdit.getEmail());

        userToEdit.setFirstName(firstName);
        userToEdit.setLastName(lastName);
        userToEdit.setEmail(email);
        userToEdit.setAvatarURL(avatarUrl);

        userToEdit.setNeedsEdit(false);
        userRepository.save(userToEdit);
        return userToEdit;
    }

    public User setFacebookAccount(String userId, String facebookAccessToken) {
        User userToEdit = repositoryUtils.validateObjectExist(User.class, userId);
        String originalEmail = userToEdit.getEmail();
        userToEdit = facebookService.updateUserFromToken(userToEdit ,facebookAccessToken);
        userToEdit.setNeedsEdit(false);
        userRepository.save(userToEdit);

        sendWelcomeEmailIfNeeded(userToEdit.getEmail(), originalEmail);

        return userToEdit;
    }

    //Private
    private void sendWelcomeEmailIfNeeded(String newEmail, String oldEmail) {
        if (newEmail != null && (oldEmail == null || !oldEmail.equals(newEmail))) {
            //Send Welcome email
            emailService.sendWelcomeMessage(newEmail);
        }
    }

}

package application.security;

import application.model.PhoneNumberChallenge;
import application.model.Token;
import application.model.User;
import application.repositories.phoneNumberChallenge.PhoneNumberChallengeRepository;
import application.repositories.token.TokenRepository;
import application.repositories.user.UserRepository;
import application.restControllers.exceptions.ObjectNotFoundException;
import application.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by matan on 20/05/2016.
 */

@Component
public class Authenticator {

    private static final Logger log = Logger.getLogger( Authenticator.class.getName() );

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PhoneNumberChallengeRepository phoneNumberChallengeRepository;


    public User verifyAuthentication(String userAccessToken) {
        Token token = tokenRepository.findByAccessToken(userAccessToken).orElse(null);
        return token != null ? token.getUser() : null;
    }

    public PhoneNumberChallenge generatePhoneNumberChallenge(String phoneNumber) {
        PhoneNumberChallenge phoneNumberChallenge = new PhoneNumberChallenge(phoneNumber);
        phoneNumberChallengeRepository.save(phoneNumberChallenge);
        return phoneNumberChallenge;
    }

    public boolean validatePhoneNumberChallenge(String phoneNumber, int verificationCode) {
        PhoneNumberChallenge phoneNumberChallenge = phoneNumberChallengeRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new ObjectNotFoundException(this.getClass().getName(), phoneNumber));

        if (!TimeUtils.inLastHour(phoneNumberChallenge.getCreatedAt())) {
            //Challenge is too old
            phoneNumberChallengeRepository.delete(phoneNumberChallenge);
            return false;
        }

        boolean isChallengeValid = phoneNumberChallenge.getVerificationCode() == verificationCode;
        if (isChallengeValid) {
            phoneNumberChallengeRepository.delete(phoneNumberChallenge);
        }
        return isChallengeValid;
    }
}
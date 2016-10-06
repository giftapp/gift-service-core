package application.security;

import application.model.PhoneNumberChallenge;
import application.repositories.phoneNumberChallenge.PhoneNumberChallengeRepository;
import application.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by matan on 20/05/2016.
 */

@Component
public class Authenticator {

    private static final Logger log = Logger.getLogger( Authenticator.class.getName() );

    @Autowired
    private PhoneNumberChallengeRepository phoneNumberChallengeRepository;

    public PhoneNumberChallenge generatePhoneNumberChallenge(String phoneNumber) {
        //Check if a challenge already exist
        Optional<PhoneNumberChallenge> existingPhoneNumberChallenge = phoneNumberChallengeRepository.findByPhoneNumber(phoneNumber);
        if (existingPhoneNumberChallenge.isPresent()) {
            if (TimeUtils.inLastHour(existingPhoneNumberChallenge.get().getCreatedAt())) {
                //Valid challenge already exist! use it.
                log.log(Level.FINE, "Reusing already existing phone number challenge code");
                return existingPhoneNumberChallenge.get();
            } else {
                //Challenge is too old
                log.log(Level.FINE, "Deleting expired phone number challenge code");
                phoneNumberChallengeRepository.delete(existingPhoneNumberChallenge.get());
            }
        }

        //Creating new challenge
        log.log(Level.FINE, "Creating new phone number challenge code");
        PhoneNumberChallenge phoneNumberChallenge = new PhoneNumberChallenge(phoneNumber);
        phoneNumberChallengeRepository.save(phoneNumberChallenge);
        return phoneNumberChallenge;
    }

}
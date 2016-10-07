package application.security;

import application.model.PhoneNumberChallenge;
import application.repositories.phoneNumberChallenge.PhoneNumberChallengeRepository;
import application.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by matan on 20/05/2016.
 */

@Component
public class Authenticator {

    private static final Logger logger = LoggerFactory.getLogger(Authenticator.class);

    @Autowired
    private PhoneNumberChallengeRepository phoneNumberChallengeRepository;

    public PhoneNumberChallenge generatePhoneNumberChallenge(String phoneNumber) {
        //Check if a challenge already exist
        Optional<PhoneNumberChallenge> existingPhoneNumberChallenge = phoneNumberChallengeRepository.findByPhoneNumber(phoneNumber);
        if (existingPhoneNumberChallenge.isPresent()) {
            if (TimeUtils.inLastHour(existingPhoneNumberChallenge.get().getCreatedAt())) {
                //Valid challenge already exist! use it.
                logger.debug("Reusing already existing phone number challenge code");
                return existingPhoneNumberChallenge.get();
            } else {
                //Challenge is too old
                logger.debug("Deleting expired phone number challenge code");
                phoneNumberChallengeRepository.delete(existingPhoneNumberChallenge.get());
            }
        }

        //Creating new challenge
        logger.debug("Creating new phone number challenge code");
        PhoneNumberChallenge phoneNumberChallenge = new PhoneNumberChallenge(phoneNumber);
        phoneNumberChallengeRepository.save(phoneNumberChallenge);
        return phoneNumberChallenge;
    }

}
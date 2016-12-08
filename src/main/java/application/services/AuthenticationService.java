package application.services;

import application.model.PhoneNumberChallenge;
import application.model.Token;
import application.outbound.sms.SMSService;
import application.repositories.token.TokenRepository;
import application.security.Authenticator;
import application.security.authentication.AuthenticationWithToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * Created by matan,
 * On 08/12/2016.
 */

@Service
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private Authenticator authenticator;

    @Autowired
    private SMSService smsService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    @Qualifier(value = "giftAuthenticationManager")
    private AuthenticationManager authenticationManager;

    public void verifyPhoneNumber(String phoneNumber) {
        //create a challenge in db
        PhoneNumberChallenge phoneNumberChallenge = authenticator.generatePhoneNumberChallenge(phoneNumber);

        logger.debug("Sending SMS with verification code: " + phoneNumberChallenge.getVerificationCode()); //TODO: remove before production
        smsService.sendVerificationSMS(phoneNumberChallenge.getPhoneNumber(), phoneNumberChallenge.getVerificationCode());
    }

    public Token getTokenWithPhoneNumberChallenge(String phoneNumber, String verificationCode) throws AuthenticationException {
        // Perform the security
        final AuthenticationWithToken authentication = (AuthenticationWithToken) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        phoneNumber,
                        verificationCode
                )
        );
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadCredentialsException("Invalid Verification Code Credentials");
        }

        // Return the token
        logger.debug("Successfully authenticated with phone number challenge");
        String accessToken = authentication.getAccessToken();
        Token token = tokenRepository.findByAccessToken(accessToken).orElseThrow(() -> new InternalError("")); //TODO: proper exception
        return token;
    }

}

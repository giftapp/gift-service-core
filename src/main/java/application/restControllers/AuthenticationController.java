package application.restControllers;

import application.model.PhoneNumberChallenge;
import application.model.Token;
import application.outbound.sms.SMSService;
import application.repositories.token.TokenRepository;
import application.restControllers.dto.request.impl.PhoneNumberAuthenticationRequestDTOImpl;
import application.restControllers.dto.request.impl.VerifyPhoneNumberRequestDTOImpl;
import application.restControllers.dto.response.impl.TokenResponseDTOImpl;
import application.security.Authenticator;
import application.security.authentication.AuthenticationWithToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by matan,
 * On 25/05/2016.
 */

@RestController
public class AuthenticationController implements AuthenticationControllerAPI {
    private static final Logger log = Logger.getLogger( AuthenticationController.class.getName() );

    @Autowired
    private Authenticator authenticator;

    @Autowired
    private SMSService smsService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    @Qualifier(value = "giftAuthenticationManager")
    private AuthenticationManager authenticationManager;

    //POST
    @Override
    public ResponseEntity verifyPhoneNumber(@Valid @NotNull @RequestBody VerifyPhoneNumberRequestDTOImpl verifyPhoneNumberRequestDTO) {
        //create a challenge in db
        PhoneNumberChallenge phoneNumberChallenge = authenticator.generatePhoneNumberChallenge(verifyPhoneNumberRequestDTO.getPhoneNumber());

        log.log(Level.INFO, "Sending SMS with verification code: " + phoneNumberChallenge.getVerificationCode());
        smsService.sendVerificationSMS(phoneNumberChallenge.getPhoneNumber(), phoneNumberChallenge.getVerificationCode());
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity getTokenWithPhoneNumberChallenge(@Valid @NotNull @RequestBody PhoneNumberAuthenticationRequestDTOImpl phoneNumberAuthenticationRequest) throws AuthenticationException {
        // Perform the security
        try {
            final AuthenticationWithToken authentication = (AuthenticationWithToken) authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            phoneNumberAuthenticationRequest.getPhoneNumber(),
                            phoneNumberAuthenticationRequest.getVerificationCode()
                    )
            );
            if (authentication == null || !authentication.isAuthenticated()) {
                log.log(Level.FINE, "Failed to authenticated with phone number challenge");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            // Return the token
            log.log(Level.FINE, "Successfully authenticated with phone number challenge");
            String accessToken = authentication.getAccessToken();
            Token token = tokenRepository.findByAccessToken(accessToken).orElseThrow(() -> new InternalError("")); //TODO: proper exception
            return ResponseEntity.ok(new TokenResponseDTOImpl(token));
        } catch (AuthenticationException e) {
            log.log(Level.FINE, "Failed to authenticated with phone number challenge");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}

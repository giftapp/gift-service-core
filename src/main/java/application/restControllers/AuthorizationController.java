package application.restControllers;

import application.model.PhoneNumberChallenge;
import application.model.Token;
import application.outbound.sms.SMSService;
import application.repositories.token.TokenRepository;
import application.restControllers.dto.request.PhoneNumberAuthenticationRequestDTO;
import application.restControllers.dto.request.VerifyPhoneNumberRequestDTO;
import application.restControllers.dto.response.impl.TokenResponseDTOImpl;
import application.security.Authenticator;
import application.security.authentication.AuthenticationWithToken;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/authorize")
public class AuthorizationController {
    private static final Logger log = Logger.getLogger( AuthorizationController.class.getName() );

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
    @ApiOperation(
            value = "Verify a phone number",
            notes = "This method will be the first API call for clients." +
                    "The client will provide a phone number which should be authenticated via SMS",
            code = 202)
    @RequestMapping(path = "/phoneNumberChallenge", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity verifyPhoneNumber(@Valid @NotNull @RequestBody VerifyPhoneNumberRequestDTO verifyPhoneNumberRequestDTO) {

        //create a challenge in db
        PhoneNumberChallenge phoneNumberChallenge = authenticator.generatePhoneNumberChallenge(verifyPhoneNumberRequestDTO.getPhoneNumber());

        log.log(Level.INFO, "Sending SMS with verification code: " + phoneNumberChallenge.getVerificationCode());
        smsService.sendVerificationSMS(phoneNumberChallenge.getPhoneNumber(), phoneNumberChallenge.getVerificationCode());
        return ResponseEntity.accepted().build();
    }

    @ApiOperation(
            value = "Get token with phone number challenge",
            notes = "This method will be called by by clients at initial login, \n" +
                    "after the client possess a verification code that was sent via SMS. \n" +
                    "As a result client will get a token object which he can later use in order to consume gift API",
            response = TokenResponseDTOImpl.class)
    @RequestMapping(path = "/token", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTokenWithPhoneNumberChallenge(@Valid @NotNull @RequestBody PhoneNumberAuthenticationRequestDTO phoneNumberAuthenticationRequest) throws AuthenticationException {

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

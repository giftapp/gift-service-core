package application.restControllers;

import application.model.PhoneNumberChallenge;
import application.model.Token;
import application.model.User;
import application.outbound.sms.SMSService;
import application.repositories.token.TokenRepository;
import application.repositories.user.UserRepository;
import application.restControllers.exceptions.UnauthorizedUserException;
import application.security.Authenticator;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    //GET
    @RequestMapping(path = "/token", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Token getToken(@RequestParam String phoneNumber, @RequestParam Integer verificationCode) {
        //verify challenge
        if (!authenticator.validatePhoneNumberChallenge(phoneNumber, verificationCode)) {
            throw new UnauthorizedUserException();
        }

        //generate token and return
        User user = userRepository.findByPhoneNumber(phoneNumber).orElse(new User(phoneNumber));
        userRepository.save(user);

        Token token = new Token(user);
        tokenRepository.save(token);

        return token;
    }

    //POST
    @RequestMapping(path = "/phoneNumberChallenge", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void verifyPhoneNumber(@Valid @RequestBody VerifyPhoneNumberRequestBody verifyPhoneNumberRequestBody) {

        //create a challenge in db
        PhoneNumberChallenge phoneNumberChallenge = authenticator.generatePhoneNumberChallenge(verifyPhoneNumberRequestBody.getPhoneNumber());

        log.log(Level.INFO, "Sending SMS with verification code: " + phoneNumberChallenge.getVerificationCode());
        smsService.sendVerificationSMS(phoneNumberChallenge.getPhoneNumber(), phoneNumberChallenge.getVerificationCode());
    }

    private static final class VerifyPhoneNumberRequestBody {
        @NotNull
        private String phoneNumber;

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @JsonCreator
        public VerifyPhoneNumberRequestBody(@JsonProperty("phoneNumber") String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }
}

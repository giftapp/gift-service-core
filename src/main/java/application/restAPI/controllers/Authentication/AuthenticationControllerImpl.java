package application.restAPI.controllers.Authentication;

import application.model.Token;
import application.restAPI.controllers.Authentication.dto.request.PhoneNumberAuthenticationRequestDTO;
import application.restAPI.controllers.Authentication.dto.request.VerifyPhoneNumberRequestDTO;
import application.restAPI.controllers.Authentication.dto.response.TokenResponseDTOImpl;
import application.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by matan,
 * On 25/05/2016.
 */

@RestController
public class AuthenticationControllerImpl implements AuthenticationControllerAPI {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationControllerImpl.class);

    @Autowired
    private AuthenticationService authenticationService;

    //POST
    @Override
    public ResponseEntity verifyPhoneNumber(@Valid @NotNull @RequestBody VerifyPhoneNumberRequestDTO verifyPhoneNumberRequestDTO) {
        authenticationService.verifyPhoneNumber(verifyPhoneNumberRequestDTO.getPhoneNumber());
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity getTokenWithPhoneNumberChallenge(@Valid @NotNull @RequestBody PhoneNumberAuthenticationRequestDTO phoneNumberAuthenticationRequest) throws AuthenticationException {
        Token token = authenticationService.getTokenWithPhoneNumberChallenge(phoneNumberAuthenticationRequest.getPhoneNumber(), phoneNumberAuthenticationRequest.getVerificationCode());
        return ResponseEntity.ok(new TokenResponseDTOImpl(token));
    }

}

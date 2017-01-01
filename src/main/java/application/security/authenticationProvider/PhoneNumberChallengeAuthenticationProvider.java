package application.security.authenticationProvider;

import application.model.PhoneNumberChallenge;
import application.model.Token;
import application.model.User;
import application.repositories.phoneNumberChallenge.PhoneNumberChallengeRepository;
import application.repositories.token.TokenRepository;
import application.repositories.user.UserRepository;
import application.security.authentication.AuthenticationWithToken;
import application.security.utils.JwtTokenUtil;
import application.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

/**
 * AuthenticationProvider implementation for {@link UsernamePasswordAuthenticationToken} authentication.
 * This class will authenticate a user that logs in with a phone number challenge.
 *
 * Created by matan,
 * On 05/10/2016.
 */
@Component
public class PhoneNumberChallengeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PhoneNumberChallengeRepository phoneNumberChallengeRepository;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${security.shouldAcceptDefaultVerificationCode}")
    private Boolean shouldAcceptDefaultVerificationCode;

    @Value("${security.defaultVerificationCode}")
    private String defaultVerificationCode;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional OptionalPhoneNumber = Optional.ofNullable(authentication.getPrincipal());
        Optional OptionalVerificationCode = Optional.ofNullable(authentication.getCredentials());

        if (!OptionalPhoneNumber.isPresent() || !OptionalVerificationCode.isPresent()) {
            throw new BadCredentialsException("Invalid Verification Code Credentials");
        }

        String phoneNumber = (String) OptionalPhoneNumber.get();
        String verificationCode = (String) OptionalVerificationCode.get();


        //TODO: maybe refactor into Authenticator
        PhoneNumberChallenge phoneNumberChallenge = phoneNumberChallengeRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new BadCredentialsException("Invalid Verification Code Credentials"));

        if (!TimeUtils.inLastHour(phoneNumberChallenge.getCreatedAt())) {
            //Challenge is too old
            phoneNumberChallengeRepository.delete(phoneNumberChallenge);
            throw new BadCredentialsException("Challenge has been expired");
        }

        if (!phoneNumberChallenge.getVerificationCode().equals(verificationCode) && !(shouldAcceptDefaultVerificationCode && verificationCode.equals(defaultVerificationCode))) {
            throw new BadCredentialsException("Invalid Verification Code Credentials");
        }

        //Successful challenge
        phoneNumberChallengeRepository.delete(phoneNumberChallenge);

        //Get user
        User user = userRepository.findByPhoneNumber(phoneNumber).orElse(new User(phoneNumber));
        userRepository.save(user);

        //Create token
        Token token = null;
        try {
            token = new Token(user, jwtTokenUtil.generateToken(user));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new InternalError("Unable to create token");
        }
        tokenRepository.save(token);

        //Return AuthenticationWithToken
        AuthenticationWithToken authenticationWithToken = new AuthenticationWithToken(user.getId(), null, user.getAuthorities());
        authenticationWithToken.setAccessToken(token.getAccessToken());
        return authenticationWithToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
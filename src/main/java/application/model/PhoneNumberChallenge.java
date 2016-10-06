package application.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by matan,
 * On 25/05/2016.
 */

@Document
public class PhoneNumberChallenge extends PersistedObject {

    private static final int MIN_CHALLENGE = 10000;
    private static final int MAX_CHALLENGE = 99999;

    @Indexed
    @NotNull
    private String phoneNumber;

    @NotNull
    private String verificationCode;

    public PhoneNumberChallenge(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.verificationCode = Integer.toString(ThreadLocalRandom.current().nextInt(MIN_CHALLENGE, MAX_CHALLENGE + 1));
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}

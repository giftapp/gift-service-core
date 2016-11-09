package application.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by matan,
 * On 25/05/2016.
 */

@Entity
public class PhoneNumberChallenge extends PersistedObject {

    private static final int MIN_CHALLENGE = 10000;
    private static final int MAX_CHALLENGE = 99999;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String verificationCode;

    protected PhoneNumberChallenge() {
    }

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

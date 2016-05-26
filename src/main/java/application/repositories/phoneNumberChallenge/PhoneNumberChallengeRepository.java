package application.repositories.phoneNumberChallenge;

import application.model.PhoneNumberChallenge;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by matan,
 * On 25/05/2016.
 */

@Repository
public interface PhoneNumberChallengeRepository extends MongoRepository<PhoneNumberChallenge, Long> {

    Optional<PhoneNumberChallenge> findByPhoneNumber(String phoneNumber);

}

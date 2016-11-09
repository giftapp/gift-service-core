package application.repositories.phoneNumberChallenge;

import application.model.PhoneNumberChallenge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by matan,
 * On 25/05/2016.
 */

@Repository
@Transactional
public interface PhoneNumberChallengeRepository extends CrudRepository<PhoneNumberChallenge, String> {

    Optional<PhoneNumberChallenge> findByPhoneNumber(String phoneNumber);

}

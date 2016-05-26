package application.repositories.token;

import application.model.Token;
import application.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by matan,
 * On 26/05/2016.
 */

@Repository
public interface TokenRepository extends MongoRepository<Token, Long> {

    Optional<Token> findById(ObjectId objectId);
    Optional<Token> findByAccessToken(String accessToken);
    Optional<Token> findByUser(User user);
}
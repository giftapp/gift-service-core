package application.repositories.token;

import application.model.Token;
import application.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by matan,
 * On 26/05/2016.
 */

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {

    Optional<Token> findById(String id);
    Optional<Token> findByAccessToken(String accessToken);
    Optional<Token> findByUser(User user);
}
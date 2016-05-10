package application.repositories.user;

import application.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Created by matan on 09/05/2016.
 */

@Repository
public interface UserRepository extends MongoRepository<User, Long>, UserRepositoryCustom{
    Optional<User> findById(ObjectId objectId);
    Optional<User> findByFirstName(String username);
}
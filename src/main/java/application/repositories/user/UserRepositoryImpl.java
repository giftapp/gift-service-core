package application.repositories.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

/**
 * Created by matan on 10/05/2016.
 */

@Component
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    MongoOperations mongoOperations;

//    @Override
//    public Optional<User> findByAccessToken(String accessToken) {
//        User user = mongoOperations.findOne(
//                Query.query(Criteria.where("token.$accessToken").is(accessToken)), User.class);
//        return Optional.ofNullable(user);
//    }
}

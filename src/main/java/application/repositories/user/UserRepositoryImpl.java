package application.repositories.user;

import org.springframework.stereotype.Component;

/**
 * Created by matan on 10/05/2016.
 */

@Component
public class UserRepositoryImpl implements UserRepositoryCustom {

//    @Override
//    public Optional<User> findByAccessToken(String accessToken) {
//        User user = mongoOperations.findOne(
//                Query.query(Criteria.where("token.$accessToken").is(accessToken)), User.class);
//        return Optional.ofNullable(user);
//    }
}

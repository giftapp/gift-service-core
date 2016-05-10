package application.repositories.user;

import application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by matan on 10/05/2016.
 */

@Component
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    MongoOperations mongoOperations;


    @Override
    public List<User> getUsersName(User users) {
        return mongoOperations.find(
                Query.query(Criteria.where("firstName").is(users.getFirstName()).and("lastName").is(users.getLastName())), User.class);
    }

}

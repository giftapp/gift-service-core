package application.repositories.gift;

import application.model.Gift;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */
public class GiftRepositoryImpl implements GiftRepositoryCustom {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<Gift> giftsForUser(ObjectId userId) {
        return mongoOperations.find(Query.query(Criteria.where("userId").in(userId)), Gift.class);
    }

}
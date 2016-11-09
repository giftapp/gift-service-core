package application.repositories.gift;

import application.model.Gift;

import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */
public class GiftRepositoryImpl implements GiftRepositoryCustom {

    @Override
    public List<Gift> giftsForUser(String userId) {
//        return mongoOperations.find(Query.query(Criteria.where("userId").in(userId)), Gift.class);
        return null;
    }

}
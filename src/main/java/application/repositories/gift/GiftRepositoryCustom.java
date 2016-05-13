package application.repositories.gift;

import application.model.Gift;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */
public interface GiftRepositoryCustom {

    List<Gift> giftsForUser(ObjectId userId);

}

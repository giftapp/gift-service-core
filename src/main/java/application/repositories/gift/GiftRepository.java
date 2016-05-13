package application.repositories.gift;

import application.model.Gift;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by matan on 13/05/2016.
 */
public interface GiftRepository extends MongoRepository<Gift, Long>, GiftRepositoryCustom {

    Optional<Gift> findById(ObjectId objectId);

}
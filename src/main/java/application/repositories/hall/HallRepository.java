package application.repositories.hall;

import application.model.Hall;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by matan on 13/05/2016.
 */
public interface HallRepository extends MongoRepository<Hall, Long> {
    Optional<Hall> findById(ObjectId objectId);
    Optional<Hall> findByGooglePlaceId(String googlePlaceId);
}
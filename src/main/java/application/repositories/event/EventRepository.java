package application.repositories.event;

import application.model.Event;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by matan on 13/05/2016.
 */
public interface EventRepository extends MongoRepository<Event, Long>, EventRepositoryCustom {

    Optional<Event> findById(ObjectId objectId);
}

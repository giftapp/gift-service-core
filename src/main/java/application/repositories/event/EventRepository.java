package application.repositories.event;

import application.model.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by matan on 13/05/2016.
 */
public interface EventRepository extends CrudRepository<Event, String>, EventRepositoryCustom {

    Optional<Event> findById(String id);
}

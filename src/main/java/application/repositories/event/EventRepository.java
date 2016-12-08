package application.repositories.event;

import application.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static application.repositories.event.EventQueries.FIND_EVENTS_FOR_DAY;
import static application.repositories.event.EventQueries.FIND_EVENTS_IN_RANGE_QUERY;

/**
 * Created by matan on 13/05/2016.
 */
public interface EventRepository extends CrudRepository<Event, String>, EventRepositoryCustom {

    Optional<Event> findById(String id);

    @Query(FIND_EVENTS_IN_RANGE_QUERY)
    Collection<Event> findEventsInRange(@Param("latitude") Double latitude,
                                       @Param("longitude") Double longitude,
                                       @Param("radius") Double radius);

    @Query(FIND_EVENTS_FOR_DAY)
    Collection<Event> findEventsForDay(@Param("startTime") Date startTime,
                                       @Param("endTime") Date endTime);
}

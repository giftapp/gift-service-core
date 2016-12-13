package application.repositories.event;

import application.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static application.repositories.event.EventQueries.FIND_EVENTS_IN_RANGE_QUERY;

/**
 * Created by matan on 13/05/2016.
 */
public interface EventRepository extends CrudRepository<Event, String>, EventRepositoryCustom {

    Optional<Event> findById(String id);

    @Query(FIND_EVENTS_IN_RANGE_QUERY)
    Collection<Event> findEventsInRange(@Param("latitude") Double latitude,
                                        @Param("longitude") Double longitude,
                                        @Param("radius") Double radius,
                                        @Param("date") LocalDate date);

    Collection<Event> findEventsByDate(LocalDate date);
}

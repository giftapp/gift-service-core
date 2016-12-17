package application.repositories.venue;

import application.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static application.repositories.venue.VenueQueries.FIND_VENUS_BATCH_QUERY;
import static application.repositories.venue.VenueQueries.FIND_VENUS_IN_RANGE_QUERY;

/**
 * Created by matan on 13/05/2016.
 */
public interface VenueRepository extends CrudRepository<Venue, String> {
    Optional<Venue> findById(String id);
    Optional<Venue> findByGooglePlaceId(String googlePlaceId);

    @Query(FIND_VENUS_IN_RANGE_QUERY)
    Collection<Venue> findVenusInRange(@Param("latitude") Double latitude,
                                       @Param("longitude") Double longitude,
                                       @Param("radius") Double radius);

    @Query(FIND_VENUS_BATCH_QUERY)
    Collection<Venue> findVenusBatch(@Param("venuesId") List<String> venuesId);
}
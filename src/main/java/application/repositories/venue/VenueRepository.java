package application.repositories.venue;

import application.model.Venue;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by matan on 13/05/2016.
 */
public interface VenueRepository extends CrudRepository<Venue, String> {
    Optional<Venue> findById(String id);
    Optional<Venue> findByGooglePlaceId(String googlePlaceId);
}
package application.repositories.hall;

import application.model.Hall;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by matan on 13/05/2016.
 */
public interface HallRepository extends CrudRepository<Hall, String> {
    Optional<Hall> findById(String id);
    Optional<Hall> findByGooglePlaceId(String googlePlaceId);
}
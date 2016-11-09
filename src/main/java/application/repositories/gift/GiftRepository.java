package application.repositories.gift;

import application.model.Gift;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by matan on 13/05/2016.
 */
public interface GiftRepository extends CrudRepository<Gift, String>, GiftRepositoryCustom {

    Optional<Gift> findById(String id);

}
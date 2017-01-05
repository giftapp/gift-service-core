package application.repositories.toast;

import application.model.Toast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by matan,
 * On 04/01/2017.
 */

@Repository
@Transactional
public interface ToastRepository extends CrudRepository<Toast, String> {

    Optional<Toast> findById(String id);

}
package application.restControllers;

import application.model.Hall;
import application.repositories.hall.HallRepository;
import application.repositories.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by matan on 13/05/2016.
 */

@RestController
@RequestMapping("/hall")
public class HallController extends AuthorizedControllerBase {
    private static final Logger log = Logger.getLogger( UserController.class.getName() );

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private RepositoryUtils repositoryUtils;

    //REST ENDPOINTS
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    @RequestMapping(path = "/{hallId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hall getHall(@PathVariable String hallId) {
        return this.repositoryUtils.validateObjectExist(Hall.class, hallId);
    }
}

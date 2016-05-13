package application.restControllers;

import application.model.Hall;
import application.repositories.hall.HallRepository;
import application.restControllers.exceptions.InvalidObjectIdException;
import application.restControllers.exceptions.ObjectNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by matan on 13/05/2016.
 */

@RestController
@RequestMapping("/hall")
public class HallController {
    private static final Logger log = Logger.getLogger( UserController.class.getName() );

    @Autowired
    private HallRepository hallRepository;

    //REST ENDPOINTS
    @RequestMapping(method = RequestMethod.GET)
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    @RequestMapping(path = "/{hallId}" ,method = RequestMethod.GET)
    public Hall getHall(@PathVariable String hallId) {
        return this.validateHall(hallId);
    }

    //Utils
    private Hall validateHall(String hallId) {
        try {
            ObjectId id = new ObjectId(hallId);
            return this.hallRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(this.getClass().getName(), hallId));
        } catch (IllegalArgumentException e) {
            log.log(Level.WARNING, "Unable to parse ObjectId from: " + hallId);
            throw new InvalidObjectIdException(hallId);
        }
    }
}

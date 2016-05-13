package application.restControllers;

import application.model.Event;
import application.repositories.event.EventRepository;
import application.restControllers.exceptions.InvalidObjectIdException;
import application.restControllers.exceptions.ObjectNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/event")
public class EventController {

    private static final Logger log = Logger.getLogger( UserController.class.getName() );

    @Autowired
    @Qualifier("eventRepository")
    private EventRepository eventRepository;

    //REST ENDPOINTS
    @RequestMapping(method = RequestMethod.GET)
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @RequestMapping(path = "/{eventId}" ,method = RequestMethod.GET)
    public Event getEvent(@PathVariable String eventId) {
        return this.validateEvent(eventId);
    }

    //Utils
    private Event validateEvent(String eventId) {
        try {
            ObjectId id = new ObjectId(eventId);
            return this.eventRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(this.getClass().getName(), eventId));
        } catch (IllegalArgumentException e) {
            log.log(Level.WARNING, "Unable to parse ObjectId from: " + eventId);
            throw new InvalidObjectIdException(eventId);
        }
    }

}

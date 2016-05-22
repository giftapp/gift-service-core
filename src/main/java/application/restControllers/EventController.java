package application.restControllers;

import application.model.Event;
import application.repositories.event.EventRepository;
import application.repositories.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by matan on 13/05/2016.
 */

@RestController
@RequestMapping("/event")
public class EventController extends AuthorizedControllerBase {

    private static final Logger log = Logger.getLogger( UserController.class.getName() );

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RepositoryUtils repositoryUtils;

    //REST ENDPOINTS
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getAllEvents(@RequestParam(value="forToday", required=false, defaultValue = "false") Boolean isForToday) {
        if (isForToday) {
            return eventRepository.eventsForDay(new Date());
        }else {
            return eventRepository.findAll();
        }
    }

    @RequestMapping(path = "/{eventId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Event getEvent(@PathVariable String eventId) {
        return this.repositoryUtils.validateObjectExist(Event.class, eventId);
    }
}

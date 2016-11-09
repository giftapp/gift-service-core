package application.restAPI.controllers;

import application.model.Event;
import application.repositories.event.EventRepository;
import application.repositories.utils.RepositoryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by matan on 13/05/2016.
 */

@RestController
@RequestMapping("/event")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RepositoryUtils repositoryUtils;

    //REST ENDPOINTS
//    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Event> getAllEvents(@RequestParam(value="forToday", required=false, defaultValue = "false") Boolean isForToday) {
//        if (isForToday) {
//            return eventRepository.eventsForDay(new Date());
//        }else {
//            return eventRepository.findAll();
//        }
//    }

    @RequestMapping(path = "/{eventId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Event getEvent(@PathVariable String eventId) {
        return this.repositoryUtils.validateObjectExist(Event.class, eventId);
    }
}

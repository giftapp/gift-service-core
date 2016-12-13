package application.restAPI.controllers.Event;

import application.model.Event;
import application.restAPI.controllers.Event.dto.CreateEventRequestDTO;
import application.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * Created by matan,
 * On 08/12/2016.
 */

@RestController
public class EventControllerImpl implements EventControllerAPI {

    private static final Logger logger = LoggerFactory.getLogger(EventControllerImpl.class);

    @Autowired
    private EventService eventService;

    //REST ENDPOINTS

    //GET
    @Override
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(value="forToday", required=false, defaultValue = "true") Boolean isForToday) {
        return ResponseEntity.ok(eventService.getAllEvents(isForToday));
    }

    @Override
    @RequestMapping(path = "/{eventId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> getEvent(@PathVariable String eventId) {
        return ResponseEntity.ok(eventService.getEvent(eventId));
    }

    @Override
    @RequestMapping(path = "/nearbysearch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Event>> findEventsInRange(@RequestParam("lat") Double latitude, @RequestParam("lng") Double longitude, @RequestParam("rad") Double radius) {
        return ResponseEntity.ok(eventService.findEventsInRange(latitude, longitude, radius));
    }

    //POST
    @RequestMapping(path = "/event", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> createEvent(@Valid @RequestBody CreateEventRequestDTO createEventRequestDTO) {
        return ResponseEntity.ok(eventService.createEvent(createEventRequestDTO.getDateString(), createEventRequestDTO.getContact1(), createEventRequestDTO.getContact2(), createEventRequestDTO.getVenueId()));
    }

}

package application.restAPI.controllers.Event;

import application.model.AuthorityName;
import application.model.Event;
import application.restAPI.controllers.Event.dto.CreateEventRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */

@Api(
        description = "Event API",
        consumes = "application/json",
        produces = "application/json")
@RequestMapping("/event")
@Secured(AuthorityName.ROLE_USER_STRING)
public interface EventControllerAPI {

    //GET
    @ApiOperation(
            value = "Get all events",
            notes = "List all existing events")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Event>> getAllEvents(@RequestParam(value="forToday", required=false, defaultValue = "true") Boolean isForToday);

    @ApiOperation(
            value = "Get event",
            notes = "Get a event with a specific id")
    @RequestMapping(path = "/{eventId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Event> getEvent(@PathVariable String eventId);

    @ApiOperation(value = "Find events within a certain distance from a given latitude/longitude point")
    @RequestMapping(path = "/nearbysearch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<Event>> findEventsInRange(@ApiParam(value = "Latitude", required = true, example = "32.1036330") @RequestParam("lat") Double latitude,
                                                        @ApiParam(value = "Longitude", required = true, example = "34.8046040") @RequestParam("lng") Double longitude,
                                                        @ApiParam(value = "Radius", required = true, example = "3") @RequestParam("rad") Double radius);

    //POST
    @ApiOperation(value = "Create a new event")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Event> createEvent(@Valid @RequestBody CreateEventRequestDTO createEventRequestDTO);

}

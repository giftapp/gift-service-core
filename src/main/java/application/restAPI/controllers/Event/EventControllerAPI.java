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
    ResponseEntity<Event> getEvent(@PathVariable("eventId") String eventId);

    @ApiOperation(value = "Find today's events within a certain distance from a given latitude/longitude point")
    @RequestMapping(path = "today/nearbysearch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<Event>> findEventsInRange(@ApiParam(value = "Latitude", required = true, example = "32.1036330") @RequestParam("lat") Double latitude,
                                                        @ApiParam(value = "Longitude", required = true, example = "34.8046040") @RequestParam("lng") Double longitude,
                                                        @ApiParam(value = "Radius", required = true, example = "3") @RequestParam("rad") Double radius);

    @ApiOperation(value = "Find today's events that match a textual search")
    @RequestMapping(path = "today/textsearch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<Event>> findEventsWithKeyword(@ApiParam(value = "keyword", required = true, example = "Matan") @RequestParam("keyword") String keyword);

    @ApiOperation(value = "Find a similar events before creating one")
    @RequestMapping(path = "similar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<Event>> findSimilarEvents(
            @ApiParam(value = "contact1FirstName", required = false, example = "Matan") @RequestParam("contact1FirstName") String contact1FirstName,
            @ApiParam(value = "contact1LastName", required = false, example = "Lachmish") @RequestParam("contact1LastName") String contact1LastName,
            @ApiParam(value = "contact2FirstName", required = false, example = "Vera") @RequestParam("contact2FirstName") String contact2FirstName,
            @ApiParam(value = "contact2LastName", required = false, example = "Vilchevsky") @RequestParam("contact2LastName") String contact2LastName,
            @ApiParam(value = "VenueId", required = false, example = "Vilchevsky") @RequestParam("80ab8034-e5cf-4dbf-bb10-af3f37b93849") String VenueId);

    //POST
    @ApiOperation(value = "Create a new event")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Event> createEvent(@Valid @RequestBody CreateEventRequestDTO createEventRequestDTO);

}

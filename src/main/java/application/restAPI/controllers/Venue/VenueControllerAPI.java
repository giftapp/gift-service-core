package application.restAPI.controllers.Venue;

import application.model.AuthorityName;
import application.model.Venue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */

@Api(
        description = "Venue API",
        consumes = "application/json",
        produces = "application/json")
@RequestMapping("/venue")
@Secured(AuthorityName.ROLE_USER_STRING)
public interface VenueControllerAPI {

    //GET
    @ApiOperation(
            value = "Get all venues",
            notes = "List all existing venues")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Venue>> getAllVenues();

    @ApiOperation(
            value = "Get venue",
            notes = "Get a venue with a specific id")
    @RequestMapping(path = "/{venueId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Venue getVenue(@PathVariable String venueId);

}

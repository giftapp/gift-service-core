package application.restAPI.controllers.Venue;

import application.model.AuthorityName;
import application.model.Venue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
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
    ResponseEntity<Venue> getVenue(@PathVariable String venueId);

    @ApiOperation(value = "Find venues within a certain distance from a given latitude/longitude point")
    @RequestMapping(path = "/nearbysearch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<Venue>> findVenusInRange(@ApiParam(value = "Latitude", required = true, example = "32.1036330") @RequestParam("lat") Double latitude,
                                                       @ApiParam(value = "Longitude", required = true, example = "34.8046040") @RequestParam("lng") Double longitude,
                                                       @ApiParam(value = "Radius", required = true, example = "3") @RequestParam("rad") Double radius);

}

package application.restAPI.controllers.Venue;

import application.model.Venue;
import application.services.VenueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * Created by matan,
 * On 05/12/2016.
 */

@RestController
public class VenueControllerImpl implements VenueControllerAPI {

    private static final Logger logger = LoggerFactory.getLogger(VenueControllerAPI.class);

    @Autowired
    private VenueService venueService;

    //REST ENDPOINTS

    //GET
    @Override
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Venue>> getAllVenues() {
        return ResponseEntity.ok(venueService.getAllVenues());
    }

    @Override
    @RequestMapping(path = "/{venueId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Venue> getVenue(@PathVariable("venueId") String venueId) {
        return ResponseEntity.ok(venueService.getVenue(venueId));
    }

    @Override
    @RequestMapping(path = "/batch" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Venue>> getVenueBatch(@RequestParam("venuesId") List<String> venuesId) {
        return ResponseEntity.ok(venueService.getVenueBatch(venuesId));
    }

    @Override
    @RequestMapping(path = "/nearbysearch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Venue>> findVenusInRange(@RequestParam("lat") Double latitude, @RequestParam("lng") Double longitude, @RequestParam("rad") Double radius) {
        return ResponseEntity.ok(venueService.findVenusInRange(latitude, longitude, radius));
    }

    @Override
    @RequestMapping(path = "/textsearch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Venue>> findVenuesWithKeyword(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(venueService.findVenuesWithKeyword(keyword));
    }
}

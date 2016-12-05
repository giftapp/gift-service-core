package application.restAPI.controllers;

import application.model.Venue;
import application.repositories.utils.RepositoryUtils;
import application.repositories.venue.VenueRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */

@RestController
@RequestMapping("/venue")
public class VenueController {
    private static final Logger logger = LoggerFactory.getLogger(VenueController.class);

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private RepositoryUtils repositoryUtils;

    //REST ENDPOINTS

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Venue>> getAllVenues() {
        List<Venue> venueList = Lists.newArrayList(venueRepository.findAll());
        return ResponseEntity.ok(venueList);
    }

    @RequestMapping(path = "/{venueId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Venue getVenue(@PathVariable String venueId) {
        return this.repositoryUtils.validateObjectExist(Venue.class, venueId);
    }

}

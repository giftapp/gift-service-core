package application.services;

import application.model.Venue;
import application.repositories.utils.RepositoryUtils;
import application.repositories.venue.VenueRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by matan,
 * On 05/12/2016.
 */

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private RepositoryUtils repositoryUtils;

    public List<Venue> getAllVenues() {
        List<Venue> venueList = Lists.newArrayList(venueRepository.findAll());
        return venueList;
    }

    public Venue getVenue(String venueId) {
        Venue venue = repositoryUtils.validateObjectExist(Venue.class, venueId);
        return venue;
    }

    public Collection<Venue> getVenueBatch(List<String> venuesId) {
        Collection<Venue> venueBatch = venueRepository.findVenusBatch(venuesId);
        return venueBatch;
    }


    public Collection<Venue> findVenusInRange(Double lat, Double lng, Double rad) {
        Collection<Venue> venusInRange = venueRepository.findVenusInRange(lat, lng, rad);
        return venusInRange;
    }
}

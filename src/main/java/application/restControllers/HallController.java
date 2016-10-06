package application.restControllers;

import application.model.Hall;
import application.repositories.hall.HallRepository;
import application.repositories.utils.RepositoryUtils;
import application.restControllers.exceptions.ObjectAlreadyExistEcxeption;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by matan on 13/05/2016.
 */

@RestController
@RequestMapping("/hall")
public class HallController {
    private static final Logger log = Logger.getLogger( UserController.class.getName() );

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private RepositoryUtils repositoryUtils;

    //REST ENDPOINTS
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    @RequestMapping(path = "/{hallId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hall getHall(@PathVariable String hallId) {
        return this.repositoryUtils.validateObjectExist(Hall.class, hallId);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
    public Hall createHall(@Valid @RequestBody CreateHallRequestBody createHallRequestBody) {
        if (hallRepository.findByGooglePlaceId(createHallRequestBody.googlePlaceId).isPresent()) {
            throw new ObjectAlreadyExistEcxeption(Hall.class.getName(), createHallRequestBody.googlePlaceId);
        }

        Hall hall = new Hall(
                createHallRequestBody.googlePlaceId,
                createHallRequestBody.name,
                createHallRequestBody.address,
                createHallRequestBody.location,
                createHallRequestBody.URL,
                createHallRequestBody.imageURL);
        return hallRepository.save(hall);
    }

    private static final class CreateHallRequestBody {
        private String googlePlaceId;

        @NotNull
        private String name;

        @NotNull
        private String address;

        @NotNull
        GeoJsonPoint location;

        private String URL;

        private String imageURL;

        @JsonCreator
        public CreateHallRequestBody(@JsonProperty("googlePlaceId") String googlePlaceId, @JsonProperty("name") String name, @JsonProperty("address") String address, @JsonProperty("location") GeoJsonPoint location, @JsonProperty("URL") String URL, @JsonProperty("imageURL") String imageURL) {
            this.googlePlaceId = googlePlaceId;
            this.name = name;
            this.address = address;
            this.location = location;
            this.URL = URL;
            this.imageURL = imageURL;
        }
    }
}

package application.repositories.utils;

import application.model.*;
import application.repositories.event.EventRepository;
import application.repositories.gift.GiftRepository;
import application.repositories.toast.ToastRepository;
import application.repositories.user.UserRepository;
import application.repositories.venue.VenueRepository;
import application.restAPI.errorHandling.exceptions.InvalidObjectIdException;
import application.restAPI.errorHandling.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by matan on 22/05/2016.
 */

@Service
public class RepositoryUtils {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryUtils.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ToastRepository toasRepository;

    public <T> T validateObjectExist(Class<T> type, String id) throws InvalidObjectIdException, IllegalArgumentException, ObjectNotFoundException {
        Optional<? extends PersistedObject> result;

        if (type == User.class) {
            result = this.userRepository.findById(id);
        } else if (type == Venue.class) {
            result = this.venueRepository.findById(id);
        } else if (type == Gift.class) {
            result = this.giftRepository.findById(id);
        } else if (type == Event.class) {
            result = this.eventRepository.findById(id);
        } else if (type == Toast.class) {
            result = this.toasRepository.findById(id);
        } else {
            throw new IllegalArgumentException("Type not supported");
        }

        return type.cast(result.orElseThrow(() -> new ObjectNotFoundException(this.getClass().getName(), id)));
    }
}

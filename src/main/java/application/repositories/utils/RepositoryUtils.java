package application.repositories.utils;

import application.model.*;
import application.repositories.event.EventRepository;
import application.repositories.gift.GiftRepository;
import application.repositories.hall.HallRepository;
import application.repositories.user.UserRepository;
import application.restControllers.exceptions.InvalidObjectIdException;
import application.restControllers.exceptions.ObjectNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by matan on 22/05/2016.
 */

@Service
public class RepositoryUtils {

    private static final Logger log = Logger.getLogger( RepositoryUtils.class.getName() );

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private EventRepository eventRepository;

    public <T> T validateObjectExist(Class<T> type, String id) throws InvalidObjectIdException, IllegalArgumentException, ObjectNotFoundException {
        ObjectId objectId;
        Optional<? extends PersistedObject> result;

        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            log.log(Level.WARNING, "Unable to parse ObjectId from: " + id);
            throw new InvalidObjectIdException(id);
        }

        if (type == User.class) {
            result = this.userRepository.findById(objectId);
        } else if (type == Hall.class) {
            result = this.hallRepository.findById(objectId);
        } else if (type == Gift.class) {
            result = this.giftRepository.findById(objectId);
        } else if (type == Event.class) {
            result = this.eventRepository.findById(objectId);
        } else {
            throw new IllegalArgumentException("Type not supported");
        }

        return type.cast(result.orElseThrow(() -> new ObjectNotFoundException(this.getClass().getName(), id)));
    }
}

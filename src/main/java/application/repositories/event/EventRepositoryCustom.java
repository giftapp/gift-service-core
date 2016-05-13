package application.repositories.event;

import application.model.Event;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */
public interface EventRepositoryCustom {

    List<Event> eventsForDay(Date date);

    List<Event> eventsForUser(ObjectId userId);
}

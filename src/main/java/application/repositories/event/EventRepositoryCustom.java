package application.repositories.event;

import application.model.Event;

import java.util.Date;
import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */
public interface EventRepositoryCustom {

    List<Event> eventsForDay(Date date);

}

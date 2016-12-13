package application.services;

import application.model.Event;
import application.repositories.event.EventRepository;
import application.repositories.utils.RepositoryUtils;
import application.utils.TimeUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by matan,
 * On 08/12/2016.
 */

@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RepositoryUtils repositoryUtils;

    public List<Event> getAllEvents(Boolean isForToday) {
        if (isForToday) {
            Date date = new Date();
            Date startTime = TimeUtils.getStartOfDayTime(date);
            Date endTime = TimeUtils.getEndOfDayTime(date);
            return Lists.newArrayList(eventRepository.findEventsForDay(startTime, endTime));
        } else {
            return Lists.newArrayList(eventRepository.findAll());
        }
    }

    public Event getEvent(String eventId) {
        Event event = repositoryUtils.validateObjectExist(Event.class, eventId);
        return event;
    }

    public Collection<Event> findEventsInRange(Double lat, Double lng, Double rad) {
        Collection<Event> eventsInRange = eventRepository.findEventsInRange(lat, lng, rad);
        return eventsInRange;
    }

    public Event createEvent(Date date, String contact1, String contact2, String venueId) {
     Event event = new Event(date, contact1, contact2, venueId);
     eventRepository.save(event);
     return event;
    }

    public Event createEvent(Long dateTimestamp, String contact1, String contact2, String venueId) {
        Date eventDate;
        if (dateTimestamp != null) {
            eventDate = new Date(dateTimestamp);
        } else {
            eventDate = new Date();
        }
        return createEvent(eventDate, contact1, contact2, venueId);
    }

}

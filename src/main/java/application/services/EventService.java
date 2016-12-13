package application.services;

import application.model.Event;
import application.repositories.event.EventRepository;
import application.repositories.utils.RepositoryUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
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
            return Lists.newArrayList(eventRepository.findEventsByDate(LocalDate.now()));
        } else {
            return Lists.newArrayList(eventRepository.findAll());
        }
    }

    public Event getEvent(String eventId) {
        Event event = repositoryUtils.validateObjectExist(Event.class, eventId);
        return event;
    }

    public Collection<Event> findEventsInRange(Double lat, Double lng, Double rad) {
        LocalDate now = LocalDate.now();
        Collection<Event> eventsInRange = eventRepository.findEventsInRange(lat, lng, rad, now);
        return eventsInRange;
    }

    public Collection<Event> findEventsWithKeyword(String keyword) {
        LocalDate now = LocalDate.now();
        Collection<Event> eventsInRange = eventRepository.findEventsWithKeyword(keyword, now);
        return eventsInRange;
    }

    public Event createEvent(LocalDate date, String contact1, String contact2, String venueId) {
     Event event = new Event(date, contact1, contact2, venueId);
     eventRepository.save(event);
     return event;
    }

    public Event createEvent(String dateString, String contact1, String contact2, String venueId) {
        LocalDate eventDate = (dateString != null) ? LocalDate.parse(dateString) : LocalDate.now();
        return createEvent(eventDate, contact1, contact2, venueId);
    }

}

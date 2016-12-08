package application.repositories.event;

/**
 * Created by matan,
 * On 08/12/2016.
 */
public class EventQueries {

    static final String FIND_EVENTS_IN_RANGE_HAVERSINE_PART = "(6371 * acos(cos(radians(:latitude)) * cos(radians(v.latitude)) * cos(radians(v.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(v.latitude))))";
    static final String FIND_EVENTS_IN_RANGE_QUERY = "SELECT e FROM Event e, Venue v WHERE e.venueId = v.id AND " + FIND_EVENTS_IN_RANGE_HAVERSINE_PART +" < :radius ORDER BY "+ FIND_EVENTS_IN_RANGE_HAVERSINE_PART +" ASC";

    static final String FIND_EVENTS_FOR_DAY = "SELECT e FROM Event e WHERE e.date >= :startTime AND e.date <= :endTime";

}

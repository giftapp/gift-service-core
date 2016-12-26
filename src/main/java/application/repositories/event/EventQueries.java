package application.repositories.event;

/**
 * Created by matan,
 * On 08/12/2016.
 */
public class EventQueries {

    static final String FIND_EVENTS_IN_RANGE_HAVERSINE_PART = "(6371 * acos(cos(radians(:latitude)) * cos(radians(v.latitude)) * cos(radians(v.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(v.latitude))))";
    static final String FIND_EVENTS_IN_RANGE_QUERY = "SELECT e FROM Event e, Venue v WHERE e.date = :date AND e.venueId = v.id AND " + FIND_EVENTS_IN_RANGE_HAVERSINE_PART +" < :radius ORDER BY "+ FIND_EVENTS_IN_RANGE_HAVERSINE_PART +" ASC";

    static final String FIND_EVENTS_BY_DATE_QUERY = "SELECT e FROM Event e, Venue v WHERE e.date = :date AND e.venueId = v.id AND " +
            "(e.contact1FirstName LIKE CONCAT('%',:keyword,'%') OR " +
            "e.contact1LastName LIKE CONCAT('%',:keyword,'%') OR " +
            "e.contact2FirstName LIKE CONCAT('%',:keyword,'%') OR " +
            "e.contact2LastName LIKE CONCAT('%',:keyword,'%') OR " +
            "v.name LIKE CONCAT('%',:keyword,'%'))";

    static final String FIND_SIMILAR_EVENTS_QUERY = "SELECT e FROM Event e WHERE e.date = :date AND " +
            "(e.contact1FirstName LIKE :contact1FirstName OR e.contact1FirstName LIKE :contact2FirstName OR " +
            "e.contact1LastName LIKE :contact1LastName OR e.contact1LastName LIKE :contact2LastName OR " +
            "e.contact2FirstName LIKE :contact1FirstName OR e.contact2FirstName LIKE :contact2FirstName OR " +
            "e.contact2LastName LIKE :contact1LastName OR e.contact2LastName LIKE :contact2LastName OR " +
            "e.venueId LIKE :VenueId)";

}

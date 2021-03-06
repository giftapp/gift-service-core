package application.repositories.venue;

/**
 * Created by matan,
 * On 07/12/2016.
 */
public class VenueQueries {

    static final String FIND_VENUS_IN_RANGE_HAVERSINE_PART = "(6371 * acos(cos(radians(:latitude)) * cos(radians(v.latitude)) * cos(radians(v.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(v.latitude))))";
    static final String FIND_VENUS_IN_RANGE_QUERY = "SELECT v FROM Venue v WHERE "+ FIND_VENUS_IN_RANGE_HAVERSINE_PART +" < :radius ORDER BY "+ FIND_VENUS_IN_RANGE_HAVERSINE_PART +" ASC";

    static final String FIND_VENUES_BY_DATE_QUERY = "SELECT v FROM Venue v WHERE (v.name LIKE CONCAT('%',:keyword,'%') OR v.address LIKE CONCAT('%',:keyword,'%'))";

    static final String FIND_VENUS_BATCH_QUERY = "SELECT v FROM Venue v WHERE id in :venuesId";

}

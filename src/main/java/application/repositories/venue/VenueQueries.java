package application.repositories.venue;

/**
 * Created by matan,
 * On 07/12/2016.
 */
public class VenueQueries {

    static final String FIND_VENUS_IN_RANGE_HAVERSINE_PART = "(:R * acos(cos(radians(:latitude)) * cos(radians(v.latitude)) * cos(radians(v.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(v.latitude))))";
    static final String FIND_VENUS_IN_RANGE_QUERY = "SELECT v FROM Venue v WHERE "+ FIND_VENUS_IN_RANGE_HAVERSINE_PART +" < :radius ORDER BY "+ FIND_VENUS_IN_RANGE_HAVERSINE_PART +" ASC";

}

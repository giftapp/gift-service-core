package application.repositories.event;

import application.model.Event;
import application.utils.TimeUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */
public class EventRepositoryImpl  implements EventRepositoryCustom {

    @Override
    public List<Event> eventsForDay(Date date) {
        Date startTime = TimeUtils.getStartOfDayTime(date);
        Date endTime = TimeUtils.getEndOfDayTime(date);

//        return mongoOperations.find(Query.query(Criteria.where("date").gte(startTime).lte(endTime)), Event.class);
        return null;
    }

    @Override
    public List<Event> eventsForUser(String userId) {
//        return mongoOperations.find(Query.query(Criteria.where("usersId").in(userId)), Event.class);
        return null;
    }
}

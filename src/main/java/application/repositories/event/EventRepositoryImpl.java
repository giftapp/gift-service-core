package application.repositories.event;

import application.model.Event;
import application.utils.TimeUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */
public class EventRepositoryImpl  implements EventRepositoryCustom {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<Event> eventsForDay(Date date) {
        Date startTime = TimeUtils.getStartOfDayTime(date);
        Date endTime = TimeUtils.getEndOfDayTime(date);

        return mongoOperations.find(Query.query(Criteria.where("date").gte(startTime).lte(endTime)), Event.class);
    }

    @Override
    public List<Event> eventsForUser(ObjectId userId) {
        return mongoOperations.find(Query.query(Criteria.where("usersId").in(userId)), Event.class);
    }
}

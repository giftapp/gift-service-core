package application.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */

@Document
public class Event extends PersistedObject implements Serializable{

    @Indexed
    @NotNull
    private Date date;

    @NotNull
    private String groomName;

    @NotNull
    private String brideName;

    @Indexed
    @NotNull
    private ObjectId hallId;

    private List<ObjectId> usersId;

    public Event(Date date, String groomName, String brideName, ObjectId hallId) {
        this.date = date;
        this.groomName = groomName;
        this.brideName = brideName;
        this.hallId = hallId;
        this.usersId = new ArrayList<ObjectId>();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public String getGroomName() {
        return groomName;
    }

    public void setGroomName(String groomName) {
        this.groomName = groomName;
    }

    public String getBrideName() {
        return brideName;
    }

    public void setBrideName(String brideName) {
        this.brideName = brideName;
    }

    public ObjectId getHallId() {
        return hallId;
    }

    public void setHallId(ObjectId hallId) {
        this.hallId = hallId;
    }

    public List<ObjectId> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<ObjectId> usersId) {
        this.usersId = usersId;
    }

    public void addUser(ObjectId userId) {
        this.usersId.add(userId);
    }
}

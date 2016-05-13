package application.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    @DBRef
    @NotNull
    private Hall hall;

    private List<ObjectId> usersId;

    public Event(Date date, String groomName, String brideName, Hall hall) {
        this.date = date;
        this.groomName = groomName;
        this.brideName = brideName;
        this.hall = hall;
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

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        if (!date.equals(event.date)) return false;
        if (!groomName.equals(event.groomName)) return false;
        if (!brideName.equals(event.brideName)) return false;
        if (!hall.equals(event.hall)) return false;
        return usersId != null ? usersId.equals(event.usersId) : event.usersId == null;

    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + groomName.hashCode();
        result = 31 * result + brideName.hashCode();
        result = 31 * result + hall.hashCode();
        result = 31 * result + (usersId != null ? usersId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "date=" + date +
                ", groomName='" + groomName + '\'' +
                ", brideName='" + brideName + '\'' +
                ", hall=" + hall +
                ", usersId=" + usersId +
                '}';
    }
}

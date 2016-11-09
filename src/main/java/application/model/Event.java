package application.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */

@Entity
@Table(indexes = { @Index(name = "IDX_DATE", columnList = "date") })
public class Event extends PersistedObject {

    @NotNull
    private Date date;

    @NotNull
    private String groomName;

    @NotNull
    private String brideName;

    @NotNull
    private String hallId;

    @ElementCollection
    private List<String> usersId;

    protected Event() {
    }

    public Event(Date date, String groomName, String brideName, String hallId) {
        this.date = date;
        this.groomName = groomName;
        this.brideName = brideName;
        this.hallId = hallId;
        this.usersId = new ArrayList<String>();
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

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public List<String> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<String> usersId) {
        this.usersId = usersId;
    }

    public void addUser(String userId) {
        this.usersId.add(userId);
    }
}

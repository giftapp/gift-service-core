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
@Table(indexes = {  @Index(name = "IDX_DATE", columnList = "date"),
                    @Index(name = "IDX_CONTACTS", columnList = "contact1,contact2"),
                    @Index(name = "IDX_VENUE", columnList = "venueId")})
public class Event extends PersistedObject {

    @NotNull
    private Date date;

    @NotNull
    private String contact1;

    @NotNull
    private String contact2;

    @NotNull
    private String venueId;

    @ElementCollection
    private List<String> usersId;

    protected Event() {
    }

    public Event(Date date, String contact1, String contact2, String venueId) {
        this.date = date;
        this.contact1 = contact1;
        this.contact2 = contact2;
        this.venueId = venueId;
        this.usersId = new ArrayList<String>();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
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

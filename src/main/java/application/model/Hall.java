package application.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */

@Document
public class Hall extends PersistedObject implements Serializable {

    @Indexed
    @NotNull
    private String name;

    @Indexed
    @NotNull
    private String address;

    private String URL;

    private List<ObjectId> eventsId;

    public Hall(String name, String address, String URL) {
        this.name = name;
        this.address = address;
        this.URL = URL;
        this.eventsId = new ArrayList<ObjectId>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public List<ObjectId> getEventsId() {
        return eventsId;
    }

    public void setEventsId(List<ObjectId> eventsId) {
        this.eventsId = eventsId;
    }

    public void addEvent (ObjectId eventId) {
        this.eventsId.add(eventId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hall)) return false;

        Hall hall = (Hall) o;

        if (!name.equals(hall.name)) return false;
        if (!address.equals(hall.address)) return false;
        if (URL != null ? !URL.equals(hall.URL) : hall.URL != null) return false;
        return eventsId != null ? eventsId.equals(hall.eventsId) : hall.eventsId == null;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + (URL != null ? URL.hashCode() : 0);
        result = 31 * result + (eventsId != null ? eventsId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", URL='" + URL + '\'' +
                ", eventsId=" + eventsId +
                '}';
    }
}

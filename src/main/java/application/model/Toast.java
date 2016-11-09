package application.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by matan on 13/05/2016.
 */
@Entity
public class Toast extends PersistedObject {

    @NotNull
    private String userId;

    @NotNull
    private String eventId;

    private String text;

    protected Toast() {

    }

    public Toast(String userId, String eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

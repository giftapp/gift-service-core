package application.model;

import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;

/**
 * Created by matan on 13/05/2016.
 */

public class Toast {

    @NotNull
    private ObjectId userId;

    @NotNull
    private ObjectId eventId;

    private String text;

    public Toast(ObjectId userId, ObjectId eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getEventId() {
        return eventId;
    }

    public void setEventId(ObjectId eventId) {
        this.eventId = eventId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Toast)) return false;

        Toast toast = (Toast) o;

        if (!userId.equals(toast.userId)) return false;
        if (!eventId.equals(toast.eventId)) return false;
        return text != null ? text.equals(toast.text) : toast.text == null;

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + eventId.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Toast{" +
                "userId=" + userId +
                ", eventId=" + eventId +
                ", text='" + text + '\'' +
                '}';
    }
}

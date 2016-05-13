package application.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by matan on 13/05/2016.
 */

@Document
public class Gift extends PersistedObject implements Serializable {

    @NotNull
    private ObjectId userId;

    @NotNull
    private ObjectId eventId;

    @NotNull
    private Payment payment;

    private Toast toast;

    public Gift(ObjectId userId, ObjectId eventId) {
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Toast getToast() {
        return toast;
    }

    public void setToast(Toast toast) {
        this.toast = toast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gift)) return false;

        Gift gift = (Gift) o;

        if (!userId.equals(gift.userId)) return false;
        if (!eventId.equals(gift.eventId)) return false;
        if (!payment.equals(gift.payment)) return false;
        return toast != null ? toast.equals(gift.toast) : gift.toast == null;

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + eventId.hashCode();
        result = 31 * result + payment.hashCode();
        result = 31 * result + (toast != null ? toast.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "userId=" + userId +
                ", eventId=" + eventId +
                ", payment=" + payment +
                ", toast=" + toast +
                '}';
    }
}

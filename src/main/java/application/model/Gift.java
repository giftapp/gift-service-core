package application.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by matan on 13/05/2016.
 */

@Entity
public class Gift extends PersistedObject {

    @NotNull
    private String userId;

    @NotNull
    private String eventId;

    @OneToOne
    private Payment payment;

    @NotNull
    @OneToOne
    private Toast toast;

    protected Gift() {

    }

    public Gift(String userId, String eventId) {
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
}

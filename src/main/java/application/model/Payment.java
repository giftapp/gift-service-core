package application.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by matan on 13/05/2016.
 */
@Entity
public class Payment extends PersistedObject {

    @NotNull
    private String userId;

    @NotNull
    private String eventId;

    @NotNull
    private double amount;

    @NotNull
    private double numberOfPayments;

    protected Payment() {

    }

    public Payment(String userId, String eventId, double amount, double numberOfPayments) {
        this.userId = userId;
        this.eventId = eventId;
        this.amount = amount;
        this.numberOfPayments = numberOfPayments;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getNumberOfPayments() {
        return numberOfPayments;
    }

    public void setNumberOfPayments(double numberOfPayments) {
        this.numberOfPayments = numberOfPayments;
    }
}

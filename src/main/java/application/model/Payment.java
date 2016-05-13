package application.model;

import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;

/**
 * Created by matan on 13/05/2016.
 */

public class Payment {

    @NotNull
    private ObjectId userId;

    @NotNull
    private ObjectId eventId;

    @NotNull
    private double amount;

    @NotNull
    private double numberOfPayments;

    public Payment(ObjectId userId, ObjectId eventId, double amount, double numberOfPayments) {
        this.userId = userId;
        this.eventId = eventId;
        this.amount = amount;
        this.numberOfPayments = numberOfPayments;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;

        if (Double.compare(payment.amount, amount) != 0) return false;
        if (Double.compare(payment.numberOfPayments, numberOfPayments) != 0) return false;
        if (!userId.equals(payment.userId)) return false;
        return eventId.equals(payment.eventId);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userId.hashCode();
        result = 31 * result + eventId.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(numberOfPayments);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "userId=" + userId +
                ", eventId=" + eventId +
                ", amount=" + amount +
                ", numberOfPayments=" + numberOfPayments +
                '}';
    }
}

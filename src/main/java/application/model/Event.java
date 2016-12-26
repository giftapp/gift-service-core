package application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by matan on 13/05/2016.
 */

@Entity
@Table(indexes = {  @Index(name = "IDX_DATE", columnList = "date"),
                    @Index(name = "IDX_CONTACTS", columnList = "contact1FirstName,contact1LastName,contact2FirstName,contact2LastName"),
                    @Index(name = "IDX_VENUE", columnList = "venueId")})
public class Event extends PersistedObject {

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String contact1FirstName;

    @NotNull
    private String contact1LastName;

    @NotNull
    private String contact1PhoneNumber;

    @NotNull
    private String contact2FirstName;

    @NotNull
    private String contact2LastName;

    @NotNull
    private String contact2PhoneNumber;

    @NotNull
    private String venueId;

    @ElementCollection
    private List<String> usersId;

    protected Event() {
    }

    public Event(LocalDate date, String contact1FirstName, String contact1LastName, String contact1PhoneNumber, String contact2FirstName, String contact2LastName, String contact2PhoneNumber, String venueId) {
        this.date = date;
        this.contact1FirstName = contact1FirstName;
        this.contact1LastName = contact1LastName;
        this.contact1PhoneNumber = contact1PhoneNumber;
        this.contact2FirstName = contact2FirstName;
        this.contact2LastName = contact2LastName;
        this.contact2PhoneNumber = contact2PhoneNumber;
        this.venueId = venueId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContact1FirstName() {
        return contact1FirstName;
    }

    public void setContact1FirstName(String contact1FirstName) {
        this.contact1FirstName = contact1FirstName;
    }

    public String getContact1LastName() {
        return contact1LastName;
    }

    public void setContact1LastName(String contact1LastName) {
        this.contact1LastName = contact1LastName;
    }

    public String getContact1PhoneNumber() {
        return contact1PhoneNumber;
    }

    public void setContact1PhoneNumber(String contact1PhoneNumber) {
        this.contact1PhoneNumber = contact1PhoneNumber;
    }

    public String getContact2FirstName() {
        return contact2FirstName;
    }

    public void setContact2FirstName(String contact2FirstName) {
        this.contact2FirstName = contact2FirstName;
    }

    public String getContact2LastName() {
        return contact2LastName;
    }

    public void setContact2LastName(String contact2LastName) {
        this.contact2LastName = contact2LastName;
    }

    public String getContact2PhoneNumber() {
        return contact2PhoneNumber;
    }

    public void setContact2PhoneNumber(String contact2PhoneNumber) {
        this.contact2PhoneNumber = contact2PhoneNumber;
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

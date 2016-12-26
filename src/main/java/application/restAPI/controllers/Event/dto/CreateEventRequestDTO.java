package application.restAPI.controllers.Event.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by matan,
 * On 08/12/2016.
 */
@ApiModel(value = "CreateEventRequest")
public class CreateEventRequestDTO {

    @ApiModelProperty(value = "Event date as timestamp, if empty the event will create for the same day", name = "dateTimestamp", example = "2016-12-14")
    private String dateString;

    @ApiModelProperty(value = "Contact #1 first name", name = "contact1FirstName", example = "Matan")
    @NotEmpty(message = "Contact #1 first name cannot be null or empty")
    private String contact1FirstName;

    @ApiModelProperty(value = "Contact #1 last name", name = "contact1LastName", example = "Lachmish")
    @NotEmpty(message = "Contact #1 last name cannot be null or empty")
    private String contact1LastName;

    @ApiModelProperty(value = "Contact #1 phone number", name = "contact1PhoneNumber", example = "0523456789")
    @NotEmpty(message = "Contact #1 last name cannot be null or empty")
    private String contact1PhoneNumber;

    @ApiModelProperty(value = "Contact #2 first name", name = "contact2FirstName", example = "Vera")
    @NotEmpty(message = "Contact #2 first name cannot be null or empty")
    private String contact2FirstName;

    @ApiModelProperty(value = "Contact #2 last name", name = "contact2LastName", example = "Vilchevsky")
    @NotEmpty(message = "Contact #2 last name cannot be null or empty")
    private String contact2LastName;

    @ApiModelProperty(value = "Contact #2 phone number", name = "contact2PhoneNumber", example = "0598765432")
    @NotEmpty(message = "Contact #2 last name cannot be null or empty")
    private String contact2PhoneNumber;

    @ApiModelProperty(value = "Event venue id", name = "venueId", example = "32ee4d84-c6d0-4038-8b87-f0cd20d4b862")
    @NotEmpty(message = "Event venue cannot be null or empty")
    private String venueId;

    public CreateEventRequestDTO() {
    }

    public CreateEventRequestDTO(String dateString, String contact1FirstName, String contact1LastName, String contact1PhoneNumber, String contact2FirstName, String contact2LastName, String contact2PhoneNumber, String venueId) {
        this.dateString = dateString;
        this.contact1FirstName = contact1FirstName;
        this.contact1LastName = contact1LastName;
        this.contact1PhoneNumber = contact1PhoneNumber;
        this.contact2FirstName = contact2FirstName;
        this.contact2LastName = contact2LastName;
        this.contact2PhoneNumber = contact2PhoneNumber;
        this.venueId = venueId;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
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
}
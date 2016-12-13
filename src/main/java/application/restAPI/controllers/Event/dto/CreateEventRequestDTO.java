package application.restAPI.controllers.Event.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by matan,
 * On 08/12/2016.
 */
@ApiModel(value = "CreateEventRequest")
public class CreateEventRequestDTO {

    @ApiModelProperty(value = "Event date as timestamp", name = "dateTimestamp", example = "1481231631181")
    @NotNull(message = "Event date cannot be null")
    private Long dateTimestamp;

    @ApiModelProperty(value = "Event first contact", name = "contact1", example = "Matan Lachmish")
    @NotEmpty(message = "Event contact cannot be null or empty")
    private String contact1;

    @ApiModelProperty(value = "Event second contact", name = "contact2", example = "Vera Vilchevsky")
    @NotEmpty(message = "Event contact cannot be null or empty")
    private String contact2;

    @ApiModelProperty(value = "Event venue id", name = "venueId", example = "32ee4d84-c6d0-4038-8b87-f0cd20d4b862")
    @NotEmpty(message = "Event venue cannot be null or empty")
    private String venueId;

    public CreateEventRequestDTO() {
    }

    public CreateEventRequestDTO(Long dateTimestamp, String contact1, String contact2, String venueId) {
        this.dateTimestamp = dateTimestamp;
        this.contact1 = contact1;
        this.contact2 = contact2;
        this.venueId = venueId;
    }

    public Long getDateTimestamp() {
        return dateTimestamp;
    }

    public void setDateTimestamp(Long dateTimestamp) {
        this.dateTimestamp = dateTimestamp;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }
}
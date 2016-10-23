package application.restAPI.controllers.User.dto.request;

import application.utils.ValidationUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by Matan Lachmish,
 * On 23/10/2016.
 */
@ApiModel(value = "UpdateUserRequest")
public class UpdateUserRequestDTO {

    @ApiModelProperty(value = "User's first name", name = "firstName", example = "Matan")
    @NotEmpty(message = "First name cannot be null or empty")
    @Length(min = 3, max = 10, message = "First name can be between 3 to 10 chars")
    private String firstName;

    @ApiModelProperty(value = "User's last name", name = "lastName", example = "Lachmish")
    @NotEmpty(message = "Last name cannot be null or empty")
    @Length(min = 3, max = 10, message = "Last name can be between 3 to 10 chars")
    private String lastName;

    @ApiModelProperty(value = "User's email", name = "email", example = "matan@giftapp.co.il")
    @Email
    private String email;

    @ApiModelProperty(value = "User's phone number", name = "phoneNumber", example = "0523456789")
    @NotEmpty(message = "Phone number cannot be null or empty")
    @Pattern(regexp = ValidationUtils.IS_NUMERIC_REGEXP, message = "Phone number should contain numbers only")
    @Length(min = 10, max = 10, message = "Phone number should contain exactly 10 digits")
    private String phoneNumber;

    @ApiModelProperty(value = "User's avatar url", name = "avatarURL", example = "http://some.url.com/some/path")
    private String avatarURL;

    public UpdateUserRequestDTO() {
    }

    public UpdateUserRequestDTO(String firstName, String lastName, String email, String phoneNumber, String avatarURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatarURL = avatarURL;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}

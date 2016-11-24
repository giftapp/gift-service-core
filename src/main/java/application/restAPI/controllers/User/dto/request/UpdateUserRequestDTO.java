package application.restAPI.controllers.User.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Matan Lachmish,
 * On 23/10/2016.
 */
@ApiModel(value = "UpdateUserRequest")
public class UpdateUserRequestDTO {

    @ApiModelProperty(value = "User's first name", name = "firstName", example = "Matan")
    @NotEmpty(message = "First name cannot be null or empty")
    @Length(min = 2, max = 15, message = "First name can be between 2 to 15 chars")
    private String firstName;

    @ApiModelProperty(value = "User's last name", name = "lastName", example = "Lachmish")
    @NotEmpty(message = "Last name cannot be null or empty")
    @Length(min = 2, max = 15, message = "Last name can be between 2 to 15 chars")
    private String lastName;

    @ApiModelProperty(value = "User's email", name = "email", example = "matan@giftapp.co.il")
    @Email
    private String email;

    @ApiModelProperty(value = "User's avatar url", name = "avatarURL", example = "http://some.url.com/some/path")
    private String avatarURL;

    public UpdateUserRequestDTO() {
    }

    public UpdateUserRequestDTO(String firstName, String lastName, String email, String avatarURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}

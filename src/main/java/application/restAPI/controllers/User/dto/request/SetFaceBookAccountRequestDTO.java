package application.restAPI.controllers.User.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Matan Lachmish,
 * On 23/10/2016.
 */
@ApiModel(value = "SetFaceBookAccountRequest")
public class SetFaceBookAccountRequestDTO {

    @ApiModelProperty(value = "Facebook access token", name = "facebookAccessToken", example = "facebookAccessToken")
    @NotEmpty(message = "Facebook access token cannot be null or empty")
    private String facebookAccessToken;

    public SetFaceBookAccountRequestDTO() {
    }

    public SetFaceBookAccountRequestDTO(String facebookAccessToken) {
        this.facebookAccessToken = facebookAccessToken;
    }

    public String getFacebookAccessToken() {
        return facebookAccessToken;
    }

    public void setFacebookAccessToken(String facebookAccessToken) {
        this.facebookAccessToken = facebookAccessToken;
    }
}

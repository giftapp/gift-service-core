package application.restAPI.dto.response;

import application.model.Token;
import application.restAPI.dto.response.impl.TokenResponseDTOImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by matan,
 * On 05/10/2016.
 */
@JsonDeserialize(as = TokenResponseDTOImpl.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "TokenResponse")
public interface TokenResponseDTO {

    @JsonProperty("token")
    @ApiModelProperty(value = "Access token")
    Token getToken();

}

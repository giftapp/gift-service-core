package application.restAPI.dto.response;

import application.model.Token;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by matan,
 * On 04/10/2016.
 */
@ApiModel(value = "TokenResponse")
public class TokenResponseDTOImpl {

    @ApiModelProperty(value = "Access token")
    private Token token;

    public TokenResponseDTOImpl() {
    }

    public TokenResponseDTOImpl(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}

package application.restControllers.dto.response.impl;

import application.model.Token;
import application.restControllers.dto.response.TokenResponseDTO;

/**
 * Created by matan,
 * On 04/10/2016.
 */
public class TokenResponseDTOImpl implements TokenResponseDTO {

    private Token token;

    public TokenResponseDTOImpl() {
    }

    public TokenResponseDTOImpl(Token token) {
        this.token = token;
    }

    @Override
    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}

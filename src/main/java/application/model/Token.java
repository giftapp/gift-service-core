package application.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by matan,
 * On 25/05/2016.
 */

@Document
public class Token extends PersistedObject {

    @NotNull
    private String accessToken;

    @NotNull
    @DBRef
    private User user;

    public Token(User user) {
        this.user = user;
        this.accessToken = UUID.randomUUID().toString();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

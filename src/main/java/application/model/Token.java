package application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by matan,
 * On 25/05/2016.
 */

@Entity
public class Token extends PersistedObject {

    @NotNull
    @Column(length = 1024)
    private String accessToken;

    @NotNull
    @OneToOne
    private User user;

    protected Token() {

    }

    public Token(User user, String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
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

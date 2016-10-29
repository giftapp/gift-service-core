package application.model;

/**
 * Created by matan on 09/05/2016.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document
public class User extends PersistedObject {

    private String firstName;

    private String lastName;

    private String email;

    @Indexed(unique = true)
    @NotNull
    private String phoneNumber;

    private String avatarURL;

    @JsonIgnore
    private String facebookAccessToken;

    @JsonIgnore
    List<GrantedAuthority> authorities;

    private Boolean needsEdit;

    public User() {
    }

    public User(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.needsEdit = true;
    }

    public User(String firstName, String lastName, String email, String phoneNumber, String avatarURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatarURL = avatarURL;
        this.authorities = AuthorityUtils.createAuthorityList(AuthorityName.AuthorityNameEnum.USER.getRole());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) return;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) return;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) return;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return;
        this.phoneNumber = phoneNumber;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        if (avatarURL == null) return;
        this.avatarURL = avatarURL;
    }

    public String getFacebookAccessToken() {
        return facebookAccessToken;
    }

    public void setFacebookAccessToken(String facebookAccessToken) {
        if (facebookAccessToken == null) return;
        this.facebookAccessToken = facebookAccessToken;
    }

    public Boolean getNeedsEdit() {
        return needsEdit;
    }

    public void setNeedsEdit(Boolean needsEdit) {
        this.needsEdit = needsEdit;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}

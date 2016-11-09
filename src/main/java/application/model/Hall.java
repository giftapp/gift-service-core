package application.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by matan on 13/05/2016.
 */

@Entity
public class Hall extends PersistedObject {

    private String googlePlaceId;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
            //TODO: change to x and y
    String location;

    private String URL;

    private String imageURL;

    protected Hall() {

    }

    public Hall(String googlePlaceId, String name, String address, String location, String URL, String imageURL) {
        this.googlePlaceId = googlePlaceId;
        this.name = name;
        this.address = address;
        this.location = location;
        this.URL = URL;
        this.imageURL = imageURL;
    }

    public String getGooglePlaceId() {
        return googlePlaceId;
    }

    public void setGooglePlaceId(String googlePlaceId) {
        this.googlePlaceId = googlePlaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

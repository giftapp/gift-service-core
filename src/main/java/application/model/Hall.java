package application.model;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Created by matan on 13/05/2016.
 */

@Document
public class Hall extends PersistedObject {

    @Indexed
    private String googlePlaceId;

    @Indexed
    @NotNull
    private String name;

    @Indexed
    @NotNull
    private String address;

    @Indexed
    @NotNull
    GeoJsonPoint location;

    private String URL;

    private String imageURL;

    public Hall(String googlePlaceId, String name, String address, GeoJsonPoint location, String URL, String imageURL) {
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

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
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

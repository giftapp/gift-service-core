package application.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by matan on 13/05/2016.
 */

@Entity
@Table(indexes = { @Index(name = "IDX_LOCATION", columnList = "latitude,longitude") })
public class Venue extends PersistedObject {

    @NotNull
    @Column(unique=true)
    private String googlePlaceId;

    @NotNull
    private String name;

    @NotNull
    private String address;

    private String phoneNumber;

    @NotNull
    @Column(columnDefinition="Decimal(10,8)")
    private Double latitude;

    @NotNull
    @Column(columnDefinition="Decimal(11,8)")
    private Double longitude;

    private String googleMapsUrl;

    private String website;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private VenueApprovedState approvedState;

    enum VenueApprovedState {
        Pending, Approved, Denied
    }

    protected Venue() {

    }

    public Venue(String googlePlaceId, String name, String address, String phoneNumber, Double latitude, Double longitude, String googleMapsUrl, String website, String imageURL, VenueApprovedState approvedState) {
        this.googlePlaceId = googlePlaceId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.googleMapsUrl = googleMapsUrl;
        this.website = website;
        this.imageUrl = imageURL;
        this.approvedState = approvedState;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getGoogleMapsUrl() {
        return googleMapsUrl;
    }

    public void setGoogleMapsUrl(String googleMapsUrl) {
        this.googleMapsUrl = googleMapsUrl;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public VenueApprovedState getApprovedState() {
        return approvedState;
    }

    public void setApprovedState(VenueApprovedState approvedState) {
        this.approvedState = approvedState;
    }
}

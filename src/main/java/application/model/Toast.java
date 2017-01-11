package application.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * Created by matan on 13/05/2016.
 */
@Entity
public class Toast extends PersistedObject {

    public enum ToastFlavor {
        video, text
    }

    @NotNull
    private String userId;

    @NotNull
    private String eventId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ToastFlavor toastFlavor;

    @NotNull
    private String giftPresenters;

    private String videoUrl;

    private String imageUrl;

    private String text;

    protected Toast() {

    }

    public Toast(String userId, String eventId, ToastFlavor toastFlavor, String giftPresenters, String videoUrl, String imageUrl, String text) {
        this.userId = userId;
        this.eventId = eventId;
        this.toastFlavor = toastFlavor;
        this.giftPresenters = giftPresenters;
        this.videoUrl = videoUrl;
        this.imageUrl = imageUrl;
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public ToastFlavor getToastFlavor() {
        return toastFlavor;
    }

    public void setToastFlavor(ToastFlavor toastFlavor) {
        this.toastFlavor = toastFlavor;
    }

    public String getGiftPresenters() {
        return giftPresenters;
    }

    public void setGiftPresenters(String giftPresenters) {
        this.giftPresenters = giftPresenters;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

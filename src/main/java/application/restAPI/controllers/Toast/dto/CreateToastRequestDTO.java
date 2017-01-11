package application.restAPI.controllers.Toast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by matan,
 * On 10/01/2017.
 */

@ApiModel(value = "CreateToastRequest")
public class CreateToastRequestDTO {

    @ApiModelProperty(value = "Event id which the toast belongs to", name = "eventId", example = "076767ae-4339-4208-9fa0-9e1ef769664b")
    @NotEmpty(message = "Event id cannot be null or empty")
    private String eventId;

    @ApiModelProperty(value = "The type of toast", allowableValues="video,text", name = "toastFlavor", example = "text")
    @NotEmpty(message = "Toast flavor cannot be null or empty")
    private String toastFlavor;

    @ApiModelProperty(value = "Gift presenter", name = "giftPresenters", example = "Matan")
    @NotEmpty(message = "Gift presenter cannot be null or empty")
    private String giftPresenters;

    @ApiModelProperty(value = "Toast video URL", name = "videoUrl", example = "https://someurl.com/video.mp4")
    private String videoUrl;

    @ApiModelProperty(value = "Toast image URL", name = "imageUrl", example = "https://someurl.com/image.jpg")
    private String imageUrl;

    @ApiModelProperty(value = "Toast text", name = "text", example = "Wish you the best!")
    private String text;

    public CreateToastRequestDTO() {
    }

    public CreateToastRequestDTO(String eventId, String toastFlavor, String giftPresenters, String videoUrl, String imageUrl, String text) {
        this.eventId = eventId;
        this.toastFlavor = toastFlavor;
        this.giftPresenters = giftPresenters;
        this.videoUrl = videoUrl;
        this.imageUrl = imageUrl;
        this.text = text;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getToastFlavor() {
        return toastFlavor;
    }

    public void setToastFlavor(String toastFlavor) {
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
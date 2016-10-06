package application.restControllers.dto.request;

import application.restControllers.dto.request.impl.VerifyPhoneNumberRequestDTOImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by matan,
 * On 04/10/2016.
 */
@JsonDeserialize(as = VerifyPhoneNumberRequestDTOImpl.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "VerifyPhoneNumberRequest")
public interface VerifyPhoneNumberRequestDTO {

    @ApiModelProperty(value = "Phone number to be validated", name = "phoneNumber", example = "0501234567")
    @NotEmpty(message = "Phone number cannot be null or empty")
    @Pattern(regexp = "[0-9]+", message = "Phone number should contain numbers only")
    @Length(min = 10, max = 10, message = "Phone number should contain exactly 10 digits")
    String getPhoneNumber();

}

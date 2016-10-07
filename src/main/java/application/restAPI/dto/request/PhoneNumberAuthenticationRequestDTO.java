package application.restAPI.dto.request;

import application.restAPI.dto.request.impl.PhoneNumberAuthenticationRequestDTOImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;

/**
 * Created by matan,
 * On 06/10/2016.
 */
@JsonDeserialize(as = PhoneNumberAuthenticationRequestDTOImpl.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "PhoneNumberAuthenticationRequest")
public interface PhoneNumberAuthenticationRequestDTO {

//    @ApiModelProperty(value = "Phone number to be validated", name = "phoneNumber", example = "0501234567")
//    @NotEmpty(message = "Phone number cannot be null or empty")
//    @Pattern(regexp = ValidationUtils.IS_NUMERIC_REGEXP, message = "Phone number should contain numbers only")
//    @Length(min = 10, max = 10, message = "Phone number should contain exactly 10 digits")
//    String getPhoneNumber();
//
//    @ApiModelProperty(value = "Verification code to be validated", name = "verificationCode", example = "12345")
//    @NotEmpty(message = "Phone number cannot be null or empty")
//    @Pattern(regexp = ValidationUtils.IS_NUMERIC_REGEXP, message = "Verification code should contain numbers only")
//    @Length(min = 5, max = 5, message = "Verification code should contain exactly 5 digits")
//    String getVerificationCode();

}

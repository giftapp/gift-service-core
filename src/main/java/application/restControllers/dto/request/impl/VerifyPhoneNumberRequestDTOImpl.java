package application.restControllers.dto.request.impl;

import application.restControllers.dto.request.VerifyPhoneNumberRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by matan,
 * On 04/10/2016.
 */
@ApiModel(value = "VerifyPhoneNumberRequest")
public class VerifyPhoneNumberRequestDTOImpl implements VerifyPhoneNumberRequestDTO {

    @ApiModelProperty(value = "Phone number to be validated", name = "phoneNumber", example = "0501234567")
    @NotEmpty(message = "Phone number cannot be null or empty")
    @Pattern(regexp = "[0-9]+", message = "Phone number should contain numbers only")
    @Length(min = 10, max = 10, message = "Phone number should contain exactly 10 digits")
    private String phoneNumber;

    public VerifyPhoneNumberRequestDTOImpl() {
    }

    public VerifyPhoneNumberRequestDTOImpl(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

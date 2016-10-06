package application.restControllers.dto.request.impl;

import application.restControllers.dto.request.VerifyPhoneNumberRequestDTO;

/**
 * Created by matan,
 * On 04/10/2016.
 */
public class VerifyPhoneNumberRequestDTOImpl implements VerifyPhoneNumberRequestDTO {

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

package application.restControllers.dto.request.impl;

import application.restControllers.dto.request.PhoneNumberAuthenticationRequestDTO;

/**
 * Created by matan,
 * On 04/10/2016.
 */
public class PhoneNumberAuthenticationRequestDTOImpl implements PhoneNumberAuthenticationRequestDTO {

    private String phoneNumber;
    private String verificationCode;

    public PhoneNumberAuthenticationRequestDTOImpl() {
    }

    public PhoneNumberAuthenticationRequestDTOImpl(String phoneNumber, String verificationCode) {
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
package com.electricity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserProfileUpdateRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Consumer number is required")
    private String consumerNumber;

    private String address;

    @Size(min = 6, message = "New password must be at least 6 characters")
    private String newPassword;

    public UserProfileUpdateRequest() {
    }

    public UserProfileUpdateRequest(String name, String consumerNumber, String address, String newPassword) {
        this.name = name;
        this.consumerNumber = consumerNumber;
        this.address = address;
        this.newPassword = newPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConsumerNumber() {
        return consumerNumber;
    }

    public void setConsumerNumber(String consumerNumber) {
        this.consumerNumber = consumerNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

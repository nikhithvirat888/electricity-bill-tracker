package com.electricity.dto;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String consumerNumber;
    private String address;

    public UserDto() {
    }

    public UserDto(Long id, String name, String email, String consumerNumber, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.consumerNumber = consumerNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}

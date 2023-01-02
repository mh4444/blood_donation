package com.example.blood_donation.model;

import com.example.blood_donation.core.enums.BloodGroup;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {
    private String name;
    private String phone;
    private String password;
    private String address;
    private BloodGroup bloodGroup;
    private String gender;
    private LocalDate dateOfBirth;

    public User(String name, String phone, String password, String gender, String address, BloodGroup bloodGroup,LocalDate dateOfBirth) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.bloodGroup = bloodGroup;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



}

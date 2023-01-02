package com.example.blood_donation.core.enums;

public enum BloodGroup {
    O_POSITIVE("O+"),
    O_NEGATIVE("O-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    A_POSITIVE("A+"),
    A_NEGATIVE("A-");

    private final String bloodGroup;

    BloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getString() {
        return this.bloodGroup;
    }
}

package com.example.blood_donation.util;

import com.example.blood_donation.core.enums.BloodGroup;
import javafx.util.StringConverter;

public class BloodGroupStringConverter extends StringConverter<BloodGroup> {
    @Override
    public String toString(BloodGroup bloodGroup) {
        if (bloodGroup == null) return null;
        return bloodGroup.getString();
    }

    @Override
    public BloodGroup fromString(String s) {
        return null;
    }
}

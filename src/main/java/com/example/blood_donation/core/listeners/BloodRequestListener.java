package com.example.blood_donation.core.listeners;

import com.example.blood_donation.model.BloodRequest;
import com.example.blood_donation.model.User;

public interface BloodRequestListener {
    void helpOnClicked(BloodRequest bloodRequest, User user);
}

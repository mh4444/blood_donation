package com.example.blood_donation.model;

import com.example.blood_donation.core.enums.BloodGroup;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BloodRequest implements Serializable {
    private BloodGroup bloodGroup;
    private String note;
    private LocalDateTime createdAt;
    private User respondedBy = null;
    private User requestedBy;

    public BloodRequest(BloodGroup bloodGroup, User requestedBy, String note, LocalDateTime createdAt) {
        this.bloodGroup = bloodGroup;
        this.note = note;
        this.createdAt = createdAt;
        this.requestedBy = requestedBy;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getRespondedBy() {
        return respondedBy;
    }

    public void setRespondedBy(User respondedBy) {
        this.respondedBy = respondedBy;
    }

    public User getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(User requestedBy) {
        this.requestedBy = requestedBy;
    }
}

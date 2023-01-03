package com.example.blood_donation.core;

import com.example.blood_donation.model.BloodRequest;
import com.example.blood_donation.model.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BloodRequestManager {

    private final FileManager fileManager = new FileManager();

    public ArrayList<BloodRequest> getAllBloodRequest() {
        ArrayList<BloodRequest> bloodRequests = fileManager.loadObjectFromFile(new File(FileManager.BLOOD_REQUEST));
        if (bloodRequests == null) {
            return new ArrayList<>();
        } else {
            return bloodRequests;
        }
    }

    public List<BloodRequest> getBloodRequestByUser(User user) {
        return getAllBloodRequest().stream().filter((bloodRequest -> bloodRequest.getRequestedBy().getPhone().equals(user.getPhone()))).collect(Collectors.toList());
    }

    public boolean saveBloodRequest(ArrayList<BloodRequest> bloodRequests) {
        return fileManager.saveObjectToFile(new File(FileManager.BLOOD_REQUEST), bloodRequests);
    }

}

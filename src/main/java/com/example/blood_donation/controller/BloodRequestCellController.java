package com.example.blood_donation.controller;

import com.example.blood_donation.BloodDonationApplication;
import com.example.blood_donation.core.enums.FxmlFiles;
import com.example.blood_donation.model.BloodRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class BloodRequestCellController {
    // ListView
    Node listCellView;
    @FXML
    Label requestedBy;
    @FXML
    Label phone;
    @FXML
    Label createdAt;
    @FXML
    Label note;

    @FXML
    Button helpButton;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu, hh:mm a");

    public BloodRequestCellController() {
        initializeListCellView();
    }

    public Node getListCellView() {
        return listCellView;
    }

    private void initializeListCellView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BloodDonationApplication.class.getResource(FxmlFiles.BLOOD_REQUEST_LIST_CELL.getFileName()));
            fxmlLoader.setController(this);
            listCellView = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData(BloodRequest bloodRequest) {
        requestedBy.setText("Requested By: " + bloodRequest.getRequestedBy().getName());
        phone.setText("Phone: " + bloodRequest.getRequestedBy().getPhone());
        createdAt.setText("Request Created At: " + formatter.format(bloodRequest.getCreatedAt()));
        note.setText("Note: " + bloodRequest.getNote());
    }
}

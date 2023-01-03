package com.example.blood_donation.controller;

import com.example.blood_donation.BloodDonationApplication;
import com.example.blood_donation.core.UserManager;
import com.example.blood_donation.core.enums.FxmlFiles;
import com.example.blood_donation.core.listeners.BloodRequestListener;
import com.example.blood_donation.model.BloodRequest;
import com.example.blood_donation.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

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
    Label respondedByPhone;
    @FXML
    Label respondedBy;

    @FXML
    TextArea note;

    @FXML
    Button helpButton;

    @FXML
    Button bloodGroupType;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu, hh:mm a");

    private UserManager userManager = new UserManager();

    BloodRequestListener bloodRequestListener;

    User currentUser;

    public BloodRequestCellController(BloodRequestListener bloodRequestListener) {
        initializeListCellView();
        currentUser = userManager.getLoggedInUser();
        this.bloodRequestListener = bloodRequestListener;
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

//    @FXML
//    private void helpButtonClicked(ActionEvent actionEvent){
//        System.out.println("Help button clicked");
//    }

    public void setData(BloodRequest bloodRequest) {

        String name = bloodRequest.getRequestedBy().getName();
        boolean isOwnRequest = bloodRequest.getRequestedBy().getPhone().equals(currentUser.getPhone());
        if (isOwnRequest) {
            name = name + " (You)";
            helpButton.setVisible(false);
        } else {
            helpButton.setVisible(true);
        }

        helpButton.setOnAction(actionEvent -> bloodRequestListener.helpOnClicked(bloodRequest, currentUser));

        requestedBy.setText("Requested By: " + name);
        phone.setText("Phone: " + bloodRequest.getRequestedBy().getPhone());
        createdAt.setText("Request Created At: " + formatter.format(bloodRequest.getCreatedAt()));
        note.setText("Note: " + bloodRequest.getNote());
        bloodGroupType.setText(bloodRequest.getBloodGroup().getString());


        if (bloodRequest.getRespondedBy() != null) {
            respondedByPhone.setVisible(true);
            respondedByPhone.setManaged(true);
            respondedBy.setVisible(true);
            respondedBy.setManaged(true);

            respondedBy.setText("Responded By: " + bloodRequest.getRespondedBy().getName());
            respondedByPhone.setText("Phone: " + bloodRequest.getRespondedBy().getPhone());

        } else {
            respondedByPhone.setVisible(false);
            respondedByPhone.setManaged(false);
            respondedBy.setVisible(false);
            respondedBy.setManaged(false);
        }
    }

}

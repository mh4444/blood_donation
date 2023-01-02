package com.example.blood_donation.controller;

import com.example.blood_donation.core.BloodRequestManager;
import com.example.blood_donation.core.UserManager;
import com.example.blood_donation.core.enums.BloodGroup;
import com.example.blood_donation.core.enums.FxmlFiles;
import com.example.blood_donation.model.BloodRequest;
import com.example.blood_donation.model.User;
import com.example.blood_donation.util.AppUtils;
import com.example.blood_donation.util.BloodGroupStringConverter;
import com.example.blood_donation.util.BloodRequestListViewCell;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BloodRequestController implements Initializable {
    private UserManager userManager = new UserManager();
    private BloodRequestManager bloodRequestManager = new BloodRequestManager();
    private User currentUser;

    ArrayList<BloodRequest> bloodRequests = new ArrayList<>();

    @FXML
    VBox nothingFound;
    @FXML
    ComboBox<BloodGroup> bloodGroupComboBox;
    @FXML
    ListView<BloodRequest> bloodRequestsListView;
    @FXML
    TextArea noteTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentUser = userManager.getLoggedInUser();
        loadBloodRequests();
        setUpBloodGroupComboBox();

    }

    private void setUpBloodRequestListView() {
        if (bloodRequestsListView != null) {
            bloodRequestsListView.setItems(FXCollections.observableArrayList(bloodRequests));
            bloodRequestsListView.setCellFactory(bloodRequestListView -> new BloodRequestListViewCell());
        }
    }

    private void setUpBloodGroupComboBox() {
        if (bloodGroupComboBox == null) return;
        bloodGroupComboBox.setItems(FXCollections.observableArrayList(BloodGroup.values()));
        bloodGroupComboBox.setConverter(new BloodGroupStringConverter());
        bloodGroupComboBox.getSelectionModel().selectFirst();
    }

    public void createBloodRequest(ActionEvent actionEvent) {
        BloodRequest bloodRequest = new BloodRequest(bloodGroupComboBox.getValue(), currentUser, noteTextArea.getText(), LocalDateTime.now());
        bloodRequests.add(bloodRequest);
        boolean isSaved = bloodRequestManager.saveBloodRequest(bloodRequests);
        if (isSaved) {
            noteTextArea.setText("");
            bloodGroupComboBox.getSelectionModel().selectFirst();
            AppUtils.showAlert(Alert.AlertType.INFORMATION, "Blood Request", "Blood request created!");
        } else {
            AppUtils.showAlert(Alert.AlertType.ERROR, "Blood Request", "Something went wrong");
        }
    }

    public void loadBloodRequests() {
        bloodRequests = bloodRequestManager.getAllBloodRequest();

        if (nothingFound != null) {
            if (bloodRequests.size() > 0) {
                nothingFound.setVisible(false);
            } else {
                nothingFound.setVisible(true);
            }
        }

        bloodRequests.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
        setUpBloodRequestListView();
    }

}

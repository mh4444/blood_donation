package com.example.blood_donation.controller;

import com.example.blood_donation.core.BloodRequestManager;
import com.example.blood_donation.core.UserManager;
import com.example.blood_donation.core.enums.BloodGroup;
import com.example.blood_donation.core.listeners.BloodRequestListener;
import com.example.blood_donation.model.BloodRequest;
import com.example.blood_donation.model.User;
import com.example.blood_donation.util.AppUtils;
import com.example.blood_donation.util.BloodGroupStringConverter;
import com.example.blood_donation.util.BloodRequestListViewCell;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BloodRequestController implements Initializable, BloodRequestListener {
    private UserManager userManager = new UserManager();
    private BloodRequestManager bloodRequestManager = new BloodRequestManager();
    private User currentUser;

    ArrayList<BloodRequest> bloodRequests = new ArrayList<>();
    List<BloodRequest> userBloodRequests = new ArrayList<>();

    @FXML
    VBox nothingFound;
    @FXML
    ComboBox<BloodGroup> bloodGroupComboBox;
    @FXML
    ListView<BloodRequest> bloodRequestsListView;
    @FXML
    TextArea noteTextArea;
    @FXML
    ListView<BloodRequest> userBloodRequestListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentUser = userManager.getLoggedInUser();
        loadBloodRequests();
        loadUserBloodRequests();
        setUpBloodGroupComboBox();
    }


    private void setUpAllBloodRequestListView() {
        if (bloodRequestsListView != null) {
            bloodRequestsListView.setItems(FXCollections.observableArrayList(bloodRequests));
            bloodRequestsListView.setCellFactory(bloodRequestListView -> new BloodRequestListViewCell(this));
        }
    }


    private void setUpUserBloodRequestListView() {
        if (userBloodRequestListView != null) {
            userBloodRequestListView.setItems(FXCollections.observableArrayList(userBloodRequests));
            userBloodRequestListView.setCellFactory(bloodRequestListView -> new BloodRequestListViewCell(this));
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
            loadUserBloodRequests();
        } else {
            AppUtils.showAlert(Alert.AlertType.ERROR, "Blood Request", "Something went wrong");
        }
    }

    @FXML
    public void loadBloodRequests() {
        bloodRequests = bloodRequestManager.getAllBloodRequest();

        if (nothingFound != null) {
            if (bloodRequests.size() > 0) {
                nothingFound.setVisible(false);
            } else {
                nothingFound.setVisible(true);
            }
        }

        bloodRequests = (ArrayList<BloodRequest>) bloodRequests.stream().filter(br -> br.getRespondedBy() == null).sorted((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt())).collect(Collectors.toList());

//        bloodRequests.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
        setUpAllBloodRequestListView();
    }


    @FXML
    public void loadUserBloodRequests() {
        userBloodRequests = bloodRequestManager.getBloodRequestByUser(currentUser);
        userBloodRequests.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
        setUpUserBloodRequestListView();
    }


    @Override
    public void helpOnClicked(BloodRequest bloodRequest, User currentUser) {
        int indexToModify = bloodRequests.indexOf(bloodRequest);
        if (indexToModify > -1) {
            Optional<ButtonType> buttonType = AppUtils.showAlert(Alert.AlertType.CONFIRMATION, "Help Donating", "Are you sure you want to donate " + bloodRequest.getBloodGroup().getString() + " blood?");
            if (buttonType.get() == ButtonType.OK) {
                BloodRequest mBloodRequest = bloodRequests.get(indexToModify);
                mBloodRequest.setRespondedBy(currentUser);
                bloodRequests.set(indexToModify, bloodRequest);
                bloodRequestManager.saveBloodRequest(bloodRequests);
                loadBloodRequests();
                loadUserBloodRequests();
            }
        } else {
            System.out.println("Object not found!");
        }
    }
}

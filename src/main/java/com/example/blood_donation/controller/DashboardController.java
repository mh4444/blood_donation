package com.example.blood_donation.controller;

import com.example.blood_donation.core.NavigationManager;
import com.example.blood_donation.core.UserManager;
import com.example.blood_donation.model.User;
import com.example.blood_donation.util.AppUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DashboardController implements Initializable {
    @FXML
    public Button showBloodRequestButton;

    @FXML
    Button showBloodRequestFormButton;
    @FXML
    Button homeButton;
    @FXML
    Label currentScreen;

    @FXML
    Pane homePane;
    @FXML
    Pane bloodRequestFormPane;
    @FXML
    Pane bloodRequestsPane;

    @FXML
    Parent bloodRequest;


    @FXML
    BloodRequestController bloodRequestController;


    private NavigationManager navigationManager = new NavigationManager();

    private User currentUser;

    private ArrayList<User> registeredUsers = new ArrayList<>();

    private UserManager userManager = new UserManager();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentUser = userManager.getLoggedInUser();
        registeredUsers = userManager.loadUsers();
    }

    public void logout(ActionEvent actionEvent) {
        userManager.logoutUser();
        navigationManager.navigateToLogin(actionEvent);
    }

    public void deleteAccount(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = AppUtils.showAlert(Alert.AlertType.CONFIRMATION, "Delete account", "Are you sure you want to delete this account?");

        if (buttonType.get() == ButtonType.OK) {
            registeredUsers = (ArrayList<User>) registeredUsers.stream().filter((user -> !user.getPhone().equals(currentUser.getPhone()))).collect(Collectors.toList());
            userManager.saveUsersToFile(registeredUsers);
            logout(actionEvent);
        }

    }

    public void showHomeView(ActionEvent actionEvent) {
        currentScreen.setText("Home");
        homeButton.getStyleClass().removeAll("sidebar-button-inactive");
        showBloodRequestFormButton.getStyleClass().add("sidebar-button-inactive");
        showBloodRequestButton.getStyleClass().add("sidebar-button-inactive");

        homePane.toFront();

    }

    public void showBloodRequestFormView(ActionEvent actionEvent) {
        currentScreen.setText("Create Blood Request");
        showBloodRequestFormButton.getStyleClass().removeAll("sidebar-button-inactive");
        homeButton.getStyleClass().add("sidebar-button-inactive");
        showBloodRequestButton.getStyleClass().add("sidebar-button-inactive");

        bloodRequestController.loadUserBloodRequests();
        bloodRequestFormPane.toFront();



    }

    public void showBloodRequestView(ActionEvent actionEvent) {
        currentScreen.setText("Blood Requests");
        showBloodRequestButton.getStyleClass().removeAll("sidebar-button-inactive");
        homeButton.getStyleClass().add("sidebar-button-inactive");
        showBloodRequestFormButton.getStyleClass().add("sidebar-button-inactive");
        bloodRequestController.loadBloodRequests();
        bloodRequestsPane.toFront();

    }
}

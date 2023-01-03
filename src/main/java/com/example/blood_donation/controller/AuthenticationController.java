package com.example.blood_donation.controller;

import com.example.blood_donation.core.NavigationManager;
import com.example.blood_donation.core.UserManager;
import com.example.blood_donation.core.enums.BloodGroup;
import com.example.blood_donation.model.User;
import com.example.blood_donation.util.AppUtils;
import com.example.blood_donation.util.BloodGroupStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AuthenticationController implements Initializable {

    @FXML
    DatePicker birthDatePicker;

    @FXML
    ComboBox<String> genderComboBox;

    @FXML
    TextField loginPhone;

    @FXML
    PasswordField loginPassword;

    @FXML
    Button registerButton;

    @FXML
    Button loginButton;

    @FXML
    TextField nameTextField;

    @FXML
    TextField phoneTextField;

    @FXML
    PasswordField passwordField;

    @FXML
    TextField addressTextField;

    @FXML
    ComboBox<BloodGroup> bloodGroupComboBox;

    private ArrayList<User> registeredUsers = new ArrayList<>();

    private UserManager userManager = new UserManager();
    private NavigationManager navigationManager = new NavigationManager();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registeredUsers = userManager.loadUsers();
        setUpBloodGroupComboBox();

    }


    private void setUpBloodGroupComboBox() {
        if (bloodGroupComboBox == null) return;
        bloodGroupComboBox.setItems(FXCollections.observableArrayList(BloodGroup.values()));
        bloodGroupComboBox.setConverter(new BloodGroupStringConverter());
        bloodGroupComboBox.getSelectionModel().selectFirst();
    }

    private void saveUsers() {
        userManager.saveUsersToFile(registeredUsers);
    }

    public void goToRegister(ActionEvent actionEvent) {
        navigationManager.navigateToRegister(actionEvent);
    }

    public void goToLogin(ActionEvent actionEvent) {
        navigationManager.navigateToLogin(actionEvent);
    }

    public void signUp(ActionEvent actionEvent) {
        if (validateRegistrationForm()) {
            User user = new User(nameTextField.getText(), AppUtils.formatPhoneNumber(phoneTextField.getText()), passwordField.getText(), genderComboBox.getValue(), addressTextField.getText(), bloodGroupComboBox.getValue(), birthDatePicker.getValue());
            registeredUsers.add(user);
            saveUsers();
            AppUtils.showAlert(Alert.AlertType.INFORMATION, "Information", "Your account has been created successfully.");
            navigationManager.navigateToLogin(actionEvent);
        }
    }

    public boolean validateRegistrationForm() {
        String name = nameTextField.getText();
        String password = passwordField.getText();
        String address = addressTextField.getText();
        String phone = phoneTextField.getText();
        LocalDate dateOfBirth = birthDatePicker.getValue();

        String errorMessage;

        boolean isAlreadyRegistered = registeredUsers.stream().anyMatch((user -> user.getPhone().equals(AppUtils.formatPhoneNumber(phone))));

        if (name.trim().length() < 2) {
            errorMessage = "Name can't be smaller than 2 characters";
        } else if ((phone.trim().length() != 11 && phone.trim().length() != 14) || (!phone.startsWith("01") && !phone.startsWith("+8801"))) {
            errorMessage = "Invalid phone entered!";
        } else if (isAlreadyRegistered) {
            errorMessage = "Phone number is already registered";
        } else if (password.length() < 6) {
            errorMessage = "Minimum password length is 6";
        } else if (dateOfBirth == null) {
            errorMessage = "Valid Date of Birth is required";
        } else if (ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now()) < 15) {
            errorMessage = "You must be at least 15 years old to register";
        } else if (address.trim().length() < 3) {
            errorMessage = "Valid address is required";
        } else {
            return true;
        }

        AppUtils.showAlert(Alert.AlertType.ERROR, "Invalid Input", errorMessage);

        return false;
    }

    public void login(ActionEvent actionEvent) {

        String phone = AppUtils.formatPhoneNumber(loginPhone.getText());
        String password = loginPassword.getText();

        User validUser = registeredUsers.stream().filter(user -> user.getPhone().equals(phone) && user.getPassword().equals(password)).findFirst().orElse(null);
        if (validUser != null) {
            boolean isSaved = userManager.saveUserSession(validUser);
            if (isSaved) {
                navigationManager.navigateToHome(actionEvent);
            } else {
                AppUtils.showAlert(Alert.AlertType.ERROR, "Login failed", "Something went wrong");
            }
        } else {
            AppUtils.showAlert(Alert.AlertType.ERROR, "Login failed", "Invalid credentials");
        }

    }
}
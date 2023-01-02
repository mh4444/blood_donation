package com.example.blood_donation.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AppUtils {
    public static String formatPhoneNumber(String phone) {
        String newPhone = phone;
        if (!phone.startsWith("+880")) {
            newPhone = "+88" + phone;
        }
        return newPhone;
    }

    public static Optional<ButtonType> showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}

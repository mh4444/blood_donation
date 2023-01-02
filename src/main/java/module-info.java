module com.example.blood_donation {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.blood_donation to javafx.fxml;
    exports com.example.blood_donation;
    exports com.example.blood_donation.controller;
    exports com.example.blood_donation.model;
    opens com.example.blood_donation.controller to javafx.fxml;
    exports com.example.blood_donation.core;
    opens com.example.blood_donation.core to javafx.fxml;
    exports com.example.blood_donation.core.enums;
    opens com.example.blood_donation.core.enums to javafx.fxml;

    opens com.example.blood_donation.util;
}
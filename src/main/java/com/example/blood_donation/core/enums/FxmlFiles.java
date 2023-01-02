package com.example.blood_donation.core.enums;

public enum FxmlFiles {
    DASHBOARD("dashboard-view.fxml"),
    LOGIN("login-view.fxml"),
    REGISTER("register-view.fxml"),
    BLOOD_REQUEST_LIST_CELL("blood-requests-list-cell.fxml"),
    BLOOD_REQUEST_FORM("blood-request-form-view.fxml"),
    BLOOD_REQUEST_VIEW("blood-requests-view.fxml"),
    HOME_VIEW("home-view.fxml");

    private String fileName;

    FxmlFiles(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

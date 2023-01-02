package com.example.blood_donation.core.enums;

public enum Screens {
    DASHBOARD(FxmlFiles.DASHBOARD.getFileName(), "Home"),
    LOGIN(FxmlFiles.LOGIN.getFileName(), "Login"),
    REGISTER(FxmlFiles.REGISTER.getFileName(), "Register");
    private final String file;
    private final String title;

    Screens(String file, String title) {
        this.file = file;
        this.title = title;
    }

    public String getFile() {
        return this.file;
    }

    public String getTitle() {
        return this.title;
    }
}

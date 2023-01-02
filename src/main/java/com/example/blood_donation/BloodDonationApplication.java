package com.example.blood_donation;

import com.example.blood_donation.core.NavigationManager;
import com.example.blood_donation.core.enums.Screens;
import com.example.blood_donation.core.UserManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BloodDonationApplication extends Application {

    NavigationManager navigationManager = new NavigationManager();
    UserManager userManager = new UserManager();

    @Override
    public void start(Stage stage) throws IOException {
        Screens screen = Screens.LOGIN;

        if (userManager.getLoggedInUser() != null) {
            screen = Screens.DASHBOARD;
        }

        userManager.seedUsers();

        Scene scene = navigationManager.getScene(screen);

        stage.setResizable(false);
        stage.setTitle(screen.getTitle());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
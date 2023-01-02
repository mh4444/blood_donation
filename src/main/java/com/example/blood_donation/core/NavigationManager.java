package com.example.blood_donation.core;

import com.example.blood_donation.BloodDonationApplication;
import com.example.blood_donation.core.enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NavigationManager {

    private final int Width = 1024;
    private final int Height = 720;

    private Node getNodeFromActionEvent(ActionEvent actionEvent) {
        return (Node) (actionEvent.getSource());
    }

    private Stage getStageFromNode(Node node, String title) {
        Stage stage = (Stage) (node).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        return stage;
    }

    public Scene getScene(Screens screen) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BloodDonationApplication.class.getResource(screen.getFile()));

        return new Scene(fxmlLoader.load(), Width, Height);
    }

    private <T> void changeSceneByEvent(ActionEvent actionEvent, Screens screen) {
        changeScene(getStageFromNode(getNodeFromActionEvent(actionEvent), screen.getTitle()), screen);
    }

    private void changeScene(Stage stage, Screens screen) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(BloodDonationApplication.class.getResource(screen.getFile())));
            Scene scene = new Scene(fxmlLoader.load(), Width, Height);
            stage.setResizable(false);
            stage.setTitle(screen.getTitle());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToLogin(ActionEvent actionEvent) {
        changeSceneByEvent(actionEvent, Screens.LOGIN);
    }

    public void navigateToRegister(ActionEvent actionEvent) {
        changeSceneByEvent(actionEvent, Screens.REGISTER);
    }

    public void navigateToHome(ActionEvent actionEvent) {
        changeSceneByEvent(actionEvent, Screens.DASHBOARD);
    }

}

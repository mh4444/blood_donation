package com.example.blood_donation.controller;

import com.example.blood_donation.core.UserManager;
import com.example.blood_donation.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HomeController implements Initializable {

    @FXML
    TableColumn<User, String> nameColumn;
    @FXML
    TableColumn<User, String> bloodGroupColumn;
    @FXML
    TableColumn<User, String> phoneColumn;
    @FXML
    TableColumn<User, String> addressColumn;
    @FXML
    TableColumn<User, String> genderColumn;
    @FXML
    TableColumn<User, LocalDate> dateOfBirthColumn;

    @FXML
    TableView<User> tableView;

    @FXML
    Label currentDataSetLabel;

    private UserManager userManager = new UserManager();

    private User currentUser;

    private ArrayList<User> registeredUsers = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentUser = userManager.getLoggedInUser();
        registeredUsers = userManager.loadUsers();


        setUpTable();

    }

    private void setUpTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        bloodGroupColumn.setCellValueFactory(userBloodGroupCellDataFeatures -> new SimpleStringProperty(userBloodGroupCellDataFeatures.getValue().getBloodGroup().getString()));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        tableView.getItems().setAll(registeredUsers);
    }

    public void findByGroup(ActionEvent actionEvent) {
        String bloodGroup = ((Button) actionEvent.getSource()).getText();
        List<User> itemsToShow;
        if (bloodGroup.equals("All")) {
            itemsToShow = registeredUsers;
        } else {
            itemsToShow = registeredUsers.stream().filter((user -> user.getBloodGroup().getString().equals(bloodGroup))).collect(Collectors.toList());
        }
        currentDataSetLabel.setText("Showing records for " + bloodGroup + " blood group");
        tableView.getItems().setAll(itemsToShow);
        tableView.refresh();
    }

}

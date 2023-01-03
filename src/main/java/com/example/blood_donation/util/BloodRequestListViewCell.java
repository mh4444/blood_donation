package com.example.blood_donation.util;

import com.example.blood_donation.controller.BloodRequestCellController;
import com.example.blood_donation.core.listeners.BloodRequestListener;
import com.example.blood_donation.model.BloodRequest;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class BloodRequestListViewCell extends ListCell<BloodRequest> {

    BloodRequestListener callback;

    private final BloodRequestCellController bloodRequestController;
    private final Node view;

    public BloodRequestListViewCell(BloodRequestListener callback) {
        this.callback = callback;
        bloodRequestController = new BloodRequestCellController(callback);
        view = bloodRequestController.getListCellView();
    }


    @Override
    protected void updateItem(BloodRequest bloodRequest, boolean empty) {
        super.updateItem(bloodRequest, empty);
        if (empty || bloodRequest == null) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(view);
            bloodRequestController.setData(bloodRequest);
        }

    }
}

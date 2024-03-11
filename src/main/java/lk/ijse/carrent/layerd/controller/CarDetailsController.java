package lk.ijse.carrent.layerd.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CarDetailsController {

CarCategoryController carCategoryController;




    @FXML
    private AnchorPane anchorPaneChange;

    public void initialize() {

        try {
            addOrUpdate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnCarAddOrUpdateOnAction(ActionEvent event) throws IOException {

       addOrUpdate();

    }

    @FXML
    void btnCarDetailsOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/get_car_details-form.fxml"));
        this.anchorPaneChange  .getChildren().clear();
        this.anchorPaneChange.getChildren().add(root);


    }

    private void addOrUpdate() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/car_add_update_form.fxml"));
        this.anchorPaneChange  .getChildren().clear();
        this.anchorPaneChange.getChildren().add(root);

    }

}

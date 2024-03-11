package lk.ijse.carrent.layerd.controller.rent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RentDashboardController {
    @FXML
    private AnchorPane anchorPaneChange;

    @FXML
    void btnRentCarOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/car_rent_form.fxml"));
        this.anchorPaneChange  .getChildren().clear();
        this.anchorPaneChange.getChildren().add(root);

    }

    @FXML
    void btnCarReturnOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/car_return_from.fxml"));
        this.anchorPaneChange  .getChildren().clear();
        this.anchorPaneChange.getChildren().add(root);
    }


    @FXML
    void btnCarRentDetailsOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/car_return_details.fxml"));
        this.anchorPaneChange  .getChildren().clear();
        this.anchorPaneChange.getChildren().add(root);
    }


}

package lk.ijse.carrent.layerd.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    @FXML
    private AnchorPane anchorpaneChanges;

    @FXML
    private AnchorPane headerAnchorPane;

    @FXML
    private Label lblUserName;

    @FXML
    private AnchorPane rootNode;

    private static String userName;

    public void initialize() {
        lblUserName.setText("User : "+userName);
    }


    public void runUserId(String id){


        userName = id;
    }

    @FXML
    void btnCarCategoryOnAction(ActionEvent event) throws IOException {

        Parent root =FXMLLoader.load(getClass().getResource("/view/car_category_form.fxml"));
        this.anchorpaneChanges.getChildren().clear();
        this.anchorpaneChanges.getChildren().add(root);

    }

    @FXML
    void btnCarDetailsOnAction(ActionEvent event) throws IOException {

        Parent root =FXMLLoader.load(getClass().getResource("/view/car_details_from.fxml"));
        this.anchorpaneChanges.getChildren().clear();
        this.anchorpaneChanges.getChildren().add(root);


    }

    @FXML
    void btnDashBoardOnAction(ActionEvent event) throws IOException {
        Parent root =FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        this.anchorpaneChanges.getChildren().clear();
        this.anchorpaneChanges.getChildren().add(root);


    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Parent root =FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        this.anchorpaneChanges.getChildren().clear();
        this.anchorpaneChanges.getChildren().add(root);

    }

    @FXML
    void btnRentOnAction(ActionEvent event) throws IOException {

        Parent root =FXMLLoader.load(getClass().getResource("/view/car_rent_dashborad.fxml"));
        this.anchorpaneChanges.getChildren().clear();
        this.anchorpaneChanges.getChildren().add(root);

    }

    @FXML
    void btnSignOutOnAction(ActionEvent event) throws IOException {

        Parent root =FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));

        Stage primary = (Stage) this.rootNode.getScene().getWindow();
        primary.close();

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sign In");
        stage.centerOnScreen();
        stage.show();



    }

}

package lk.ijse.carrent.layerd.controller.rent;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.carrent.layerd.dto.RentDto;
import lk.ijse.carrent.layerd.dto.tm.CustomerRentDetailsTM;
import lk.ijse.carrent.layerd.dto.tm.LateReturnCarTm;
import lk.ijse.carrent.layerd.dto.tm.RentCarDetailsTm;
import lk.ijse.carrent.layerd.dto.tm.RentDetailsTm;
import lk.ijse.carrent.layerd.service.ServiceFactory;
import lk.ijse.carrent.layerd.service.custom.RentService;

import java.util.List;

public class CarRentDetailsController {

    @FXML
    private AnchorPane anchorPaneSearch;

    @FXML
    private AnchorPane anchorPaneTbl;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colFromDate;

    @FXML
    private TableColumn<?, ?> colRentId;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colToDate;

    @FXML
    private Label lblSearchDetails;

    @FXML
    private TableView<RentDetailsTm> tblCar;

    @FXML
    private TextField txtCarId;

    @FXML
    private Label lblCarRentDetails;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private Label lblCustomerRentDetails;

    @FXML
    private TableColumn<?, ?> colCarId;

    @FXML
    private TableColumn<?, ?> colFromDate2;

    @FXML
    private TableColumn<?, ?> colRentId2;

    @FXML
    private TableColumn<?, ?> colReturnDate2;

    @FXML
    private TableColumn<?, ?> colToDate2;

    @FXML
    private TableView<CustomerRentDetailsTM> tblCustomer;

    @FXML
    private TableColumn<?, ?> colCarId3;


    @FXML
    private TableColumn<?, ?> colCustomerId3;

    @FXML
    private TableColumn<?, ?> colFromDate3;

    @FXML
    private TableColumn<?, ?> colLateDays3;

    @FXML
    private TableColumn<?, ?> colRentId3;

    @FXML
    private TableColumn<?, ?> colReturnDate3;


    @FXML
    private TableColumn<?, ?> colToDate3;

    @FXML
    private TableView<LateReturnCarTm> tblLateReturn;




    RentService rentService = (RentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.RENT);

    public void initialize() {
        anchorPaneSearch.setVisible(false);
        anchorPaneTbl.setVisible(false);
        setValueFactory();


    }

    @FXML
    void lblCarRentDetailsOnAction(MouseEvent event) {
        txtCarId.setText("");
        anchorPaneSearch.setVisible(true);
        txtCustomerId.setVisible(false);
        txtCarId.setVisible(true);
        lblSearchDetails.setVisible(false);
        anchorPaneTbl.setVisible(false);


    }

    private void setValueFactory() {
        colRentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colFromDate.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        colToDate.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colRentId2.setCellValueFactory(new PropertyValueFactory<>("id1"));
        colFromDate2.setCellValueFactory(new PropertyValueFactory<>("fromDate1"));
        colToDate2.setCellValueFactory(new PropertyValueFactory<>("toDate1"));
        colReturnDate2.setCellValueFactory(new PropertyValueFactory<>("returnDate1"));
        colCarId3.setCellValueFactory(new PropertyValueFactory<>("carId3"));
        colRentId3.setCellValueFactory(new PropertyValueFactory<>("id3"));
        colCustomerId3.setCellValueFactory(new PropertyValueFactory<>("custId3"));
        colFromDate3.setCellValueFactory(new PropertyValueFactory<>("fromDate3"));
        colToDate3.setCellValueFactory(new PropertyValueFactory<>("toDate3"));
        colReturnDate3.setCellValueFactory(new PropertyValueFactory<>("retunDate3"));
        colLateDays3.setCellValueFactory(new PropertyValueFactory<>("lateDays3"));

    }

    @FXML
    void btnOnCarIdAction(ActionEvent event) {
        tblLateReturn.setVisible(false);
        lblSearchDetails.setVisible(true);
        getPane();
        tblCustomer.setVisible(false);
        tblCar.setVisible(true);


        lblSearchDetails.setText("");

        try {
            List<RentDto> rentDtos = rentService.getList(txtCarId.getText());
           setCarDetails(rentDtos);
            for (RentDto dto:rentDtos
                 ) {

                lblSearchDetails.setText("     "+dto.getCarBrand()+"  "+dto.getCarModel()+"  "+dto.getVehicleNumber());
                break;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }

    private void setCarDetails(List<RentDto> rentDtos) {
        ObservableList<RentDetailsTm>obl = FXCollections.observableArrayList();
        for (RentDto dto:rentDtos
             ) {
            if (dto.getRetunDate() != null) {
                obl.add(new RentDetailsTm(dto.getId(), dto.getToDate().toString(), dto.getFromDate().toString(), dto.getRetunDate().toString(), dto.getCustId()));
            }else {
                obl.add(new RentDetailsTm(dto.getId(), dto.getToDate().toString(), dto.getFromDate().toString(), "Not Yet", dto.getCustId()));
            }
        }
        tblCar.setItems(obl);
    }

    void getPane() {
        anchorPaneTbl.setVisible(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2),anchorPaneTbl);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();


    }
    void backPane(){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2),anchorPaneTbl);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }



    @FXML
    void btnCustRentDetailsOnAction(ActionEvent event) {
        lblSearchDetails.setVisible(true);
        lblSearchDetails.setText("");
        getPane();
        tblLateReturn.setVisible(false);
        tblCar.setVisible(false);
        tblCustomer.setVisible(true);
        tblCustomer.setItems(null);

        try {
            List<RentDto> rentDtos = rentService.getCustomerRentDetailsList(txtCustomerId.getText());
            setCustomerDetails(rentDtos);
            for (RentDto dto:rentDtos
                 ) {
                lblSearchDetails.setText("     "+dto.getCustName()+"   "+dto.getCustNic());
                break;
            }

            setCustomerDetails(rentDtos);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

            throw new RuntimeException(e);
        }

    }

    private void setCustomerDetails(List<RentDto> rentDtos) {
        ObservableList<CustomerRentDetailsTM> obl = FXCollections.observableArrayList();
        for (RentDto dto:rentDtos
             ) {
            if (dto.getRetunDate() != null) {
                obl.add(new CustomerRentDetailsTM(dto.getId()
                        , dto.getToDate().toString(),
                        dto.getFromDate().toString(),
                        dto.getRetunDate().toString(),
                        dto.getCarId()));
            }else {
                obl.add(new CustomerRentDetailsTM(dto.getId()
                        , dto.getToDate().toString(),
                        dto.getFromDate().toString(),
                        "Not Yet",
                        dto.getCarId()));

            }
            tblCustomer.setItems(obl);
        }
    }

    @FXML
    void lblCustomerRentDetailsOnAction(MouseEvent event) {
        txtCustomerId.setText("");
        anchorPaneSearch.setVisible(true);
        txtCarId.setVisible(false);
        lblSearchDetails.setVisible(false);
        txtCustomerId.setVisible(true);
        anchorPaneTbl.setVisible(false);

    }

    void getLateReturnList(){

        try {
            List<RentDto> rentDtos = rentService.getLateReturnList();
            setLateReturnValue(rentDtos);
            getPane();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void setLateReturnValue(List<RentDto> rentDtos) {
        ObservableList<LateReturnCarTm> obl = FXCollections.observableArrayList();
        for (RentDto dto:rentDtos
             ) {
            obl.add(new LateReturnCarTm(dto.getId(), dto.getFromDate(),dto.getToDate(),dto.getRetunDate().toString(),dto.getCustId(), dto.getCarId(), dto.getDays()));
        }
        tblLateReturn.setItems(obl);
    }

    @FXML
    void lblLateDateOnAction(MouseEvent event) {
        anchorPaneSearch.setVisible(false);
        tblCustomer.setVisible(false);
        tblCar.setVisible(false);
        tblLateReturn.setVisible(true);
        getLateReturnList();


    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        anchorPaneSearch.setVisible(false);
        backPane();

    }



}

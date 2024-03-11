package lk.ijse.carrent.layerd.controller.rent;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.carrent.layerd.dto.RentDto;
import lk.ijse.carrent.layerd.dto.tm.RentCarDetailsTm;
import lk.ijse.carrent.layerd.service.ServiceFactory;
import lk.ijse.carrent.layerd.service.custom.RentService;

import java.time.LocalDate;
import java.util.List;

public class CarReturnController {

    @FXML
    private AnchorPane anchorPaneTbl;

    @FXML
    private TableColumn<?, ?> colCarId;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colRentId;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colToretunDate;

    @FXML
    private TableColumn<?, ?> colVehicleNumber;

    @FXML
    private TableView<RentCarDetailsTm> tblAllDetails;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TextField txtRentId;

    @FXML
    private DatePicker datePickerReturnDate;

    @FXML
    private AnchorPane anchorPaneTotal;


    @FXML
    private Label lblAdvance;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblCarDetails;

    @FXML
    private Label lblCustomerDetails;

    @FXML
    private Label lblExtraDate;

    @FXML
    private Label lblNote;

    @FXML
    private Label lblRentPeriod;

    @FXML
    private Label lblTotal;


    RentService rentService = (RentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.RENT);

    public void initialize() {

        anchorPaneTotal.setVisible(false);
        setValueFactory();
        getAll();
    }

    private void setValueFactory() {
        colRentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colCarId.setCellValueFactory(new PropertyValueFactory<>("carId"));
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colToretunDate.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("retunDate"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
    }

    private void getAll(){

        try {
            List<RentDto> rentDtos = rentService.getAll();
            setCarDetails(rentDtos);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);

        }
    }

    private void setCarDetails(List<RentDto> rentDtos) {

        ObservableList<RentCarDetailsTm> obl = FXCollections.observableArrayList();
        for (RentDto dto:rentDtos
             ) {
            if(dto.getRetunDate() == null) {
            var tm = new RentCarDetailsTm(dto.getId(),dto.getToDate(), "Not Yet",dto.getUserName(), dto.getCustId(), dto.getCarId(), dto.getVehicleNumber());
            obl.add(tm);}else {
                var tm = new RentCarDetailsTm(dto.getId(),dto.getToDate(), dto.getRetunDate().toString(),dto.getUserName(), dto.getCustId(), dto.getCarId(), dto.getVehicleNumber());
                obl.add(tm);
            }

            }

        tblAllDetails.setItems(obl);
    }

    @FXML
    void tblGetIdOnAction(MouseEvent event) {

        Integer index = tblAllDetails.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            new Alert(Alert.AlertType.ERROR, "Error").show();
        } else {

            txtRentId.setText(colRentId.getCellData(index).toString());

            datePickerReturnDate.setDayCellFactory(datePicker -> new DateCell(){
                public void updateItem(LocalDate date,boolean empty){
                    super.updateItem(date,empty);
                    LocalDate today = LocalDate.parse(colToretunDate.getCellData(index).toString());
                    setDisable(empty || date.compareTo(today) < 0);
                }
            });

            SetFinalDetails(colRentId.getCellData(index).toString());

        }

    }
    @FXML
    void btnAddOnAction(ActionEvent event) {
        RentDto rentDto = new RentDto(txtRentId.getText(),datePickerReturnDate.getValue());
        try {
            String result = rentService.addReturnDate(rentDto);
            new Alert(Alert.AlertType.CONFIRMATION,result).show();
            getAll();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);

        }

    }

    void SetFinalDetails(String id){
        try {
            RentDto rentDto = rentService.getRentData(id);
            if (rentDto != null){
                lblCustomerDetails.setText("CUST ID = "+rentDto.getCustId()+" , "+"Name = "+rentDto.getCustName()+" , "+"NIC = "+rentDto.getCustNic());
                lblCarDetails.setText("CAR ID = "+rentDto.getCarId()+" , "+"Brand = "+rentDto.getCarBrand()+" , "+"Model = "+rentDto.getCarModel()+" , "+"Vehicle Number = "+rentDto.getVehicleNumber());
                lblRentPeriod.setText("From - "+rentDto.getFromDate()+"  To - "+rentDto.getToDate()+"   Return - "+rentDto.getRetunDate()+"    Price Per Day = "+rentDto.getPerDayRent());
                lblExtraDate.setText("Charge for Rent Period = Rs. "+rentDto.getChargingForRentPeriod()+"    Charge For Extra Date = Rs."+rentDto.getChargingForExtra());
                lblTotal.setText("Total = Rs. "+rentDto.getTotal());
                lblAdvance.setText("Advance = Rs. "+rentDto.getAdvancedPay()+"            Deposit = Rs. "+rentDto.getRefundableDeposit());
                lblBalance.setText("Balance = Rs. "+rentDto.getBalance());
                lblNote.setText("Your Ride Your Way - HS CAR-RENT");
                lblOn();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void lblBackOnAction(MouseEvent event) {

        lblBack();

    }

    void lblBack(){

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2),anchorPaneTotal);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

    }

    void lblOn(){
        anchorPaneTotal.setVisible(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2),anchorPaneTotal);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtRentId.setText("");
        lblBack();


    }

}

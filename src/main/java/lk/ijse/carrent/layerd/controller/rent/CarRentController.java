package lk.ijse.carrent.layerd.controller.rent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carrent.layerd.dto.CarDetailsDto;
import lk.ijse.carrent.layerd.dto.CustomerDto;
import lk.ijse.carrent.layerd.dto.RentDto;
import lk.ijse.carrent.layerd.entity.CustomerEntity;
import lk.ijse.carrent.layerd.service.ServiceFactory;
import lk.ijse.carrent.layerd.service.custom.CarDetailsSrevice;
import lk.ijse.carrent.layerd.service.custom.CustomerService;
import lk.ijse.carrent.layerd.service.custom.RentService;

import java.rmi.ServerError;
import java.time.LocalDate;
import java.util.Date;

public class CarRentController {

    @FXML
    private TextField txtCarId;

    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtPricePerDay;

    @FXML
    private TextField txtRentId;

    @FXML
    private Label lblDetailsCar;

    @FXML
    private Label lblDetailsCust;

    @FXML
    private DatePicker datePickerFromDate;

    @FXML
    private DatePicker datePickerToDate;

    @FXML
    private TextField txtDeposit;

    @FXML
    private TextField txtAdvancedPay;

    @FXML
    private AnchorPane anchorPaneTotal;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblCarDetails;

    @FXML
    private Label lblCustomerDetails;
    @FXML
    private Label lblNote;

    @FXML
    private Label lblRentPeriod;

    @FXML
    private Label lblAdvance;





    CustomerService customerService = (CustomerService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CUSTOMER);
    CarDetailsSrevice carDetailsSrevice = (CarDetailsSrevice) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CARDETAILS);
    RentService rentService = (RentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.RENT);
    static String userName;
    public void initialize() {

        datePickerFromDate.setDayCellFactory(datePicker -> new DateCell(){
            public void updateItem(LocalDate date,boolean empty){
                super.updateItem(date,empty);
                LocalDate today = LocalDate.now();
                LocalDate lastDate = today.plusDays(10);
                if(date.isBefore(today)|| date.isAfter(lastDate)){
                    setDisable(true);

                }else {
                    setDisable(false);
                    setStyle("-fx-background-color:#a29bfe;");
                }
            }
        });

        txtRentId.setText("RI0");

        lblDetailsCust.setVisible(false);
        lblDetailsCar.setVisible(false);
        anchorPaneTotal.setVisible(false);
    }

    public void runUserId(String id){


        userName = id;
    }
    @FXML
    void btnOnSearchCust(ActionEvent event) {


        try {
            CustomerDto customerDto = customerService.searchCustomer(txtCustId.getText());

                lblDetailsCust.setText(" "+customerDto.getName()+","+customerDto.getNic()+", "+customerDto.getAddress());

                lblCustomerDetails.setText("CUSTOMER = "+customerDto.getName()+","+customerDto.getNic()+","+customerDto.getAddress());
                lblCustomerDetails.setVisible(false);
                lblDetailsCust.setVisible(true);

        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Customer Id is not valid please try again").show();
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnSearchCar(ActionEvent event) {

        try {
            CarDetailsDto carDetailsDto = carDetailsSrevice.search(txtCarId.getText());
            lblDetailsCar.setText(" "+carDetailsDto.getCarCategoryName()+", "+carDetailsDto.getBrand()+", "+carDetailsDto.getModel()+", "+carDetailsDto.getVehicleNumber());
            txtPricePerDay.setText(String.valueOf(carDetailsDto.getPricePerDay()));
            lblCarDetails.setText("CAR = "+carDetailsDto.getBrand()+","+carDetailsDto.getModel()+","+carDetailsDto.getVehicleNumber()+"                                                 "+"PricePerDay = "+carDetailsDto.getPricePerDay());
            lblCarDetails.setVisible(false);
            lblDetailsCar.setVisible(true);

        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,"Car Id is not valid please try again").show();
            txtPricePerDay.setText("");

            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        try {


            RentDto rentDto = new RentDto(txtRentId.getText(),
                    Double.parseDouble(txtPricePerDay.getText()),
                    datePickerFromDate.getValue(),
                    datePickerToDate.getValue(),
                    Double.parseDouble(txtAdvancedPay.getText()),
                    Double.parseDouble(txtDeposit.getText()),
                    userName,
                    txtCustId.getText()
                    , txtCarId.getText());

            try {
                String result = rentService.addRent(rentDto);
                if (result.equals("Advance pay is Not Valid") || result.equals("Fail Added") || result.equals("This car is out ") || result.equals("Customer is not Available this time period")) {
                } else {
                    totalAndBalance(datePickerFromDate.getValue(), datePickerToDate.getValue(), Double.parseDouble(txtPricePerDay.getText()), Double.parseDouble(txtAdvancedPay.getText()));
                    lblAdvance.setText("ADVANCE = RS. " + rentDto.getAdvancedPay() + "                                                    " + "DEPOSIT = RS. " + rentDto.getRefundableDeposit());
                }

                new Alert(Alert.AlertType.CONFIRMATION, result).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }catch (Exception ex){
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        }

    }

    @FXML
    void dpGetFromDateOnAction(ActionEvent event) {



        datePickerToDate.setDayCellFactory(datePicker -> new DateCell(){
            public void updateItem(LocalDate date,boolean empty){
                super.updateItem(date,empty);
                LocalDate fromDateValue = datePickerFromDate.getValue();
                LocalDate upDate = datePickerFromDate.getValue().plusDays(30);
                if(date.isBefore(fromDateValue)|| date.isAfter(upDate)){
                    setDisable(true);

                }else {
                    setDisable(false);
                    setStyle("-fx-background-color:#a29bfe;");
                }
            }
        });




    }

    void totalAndBalance(LocalDate from,LocalDate to,Double perDayRen,Double advance){
        RentDto rentDto = new RentDto(perDayRen,from,to,advance);

        try {
            rentDto = rentService.totalAndBalance(rentDto);
            lblRentPeriod.setText("RENT PERIOD =    FROM - "+from+"            "+"TO - "+to);
            lblNote.setText("NOTE - This may vary depending on the day the vehicle is returned");
            lblTotal.setText("TOTAL = RS "+rentDto.getTotal());
            lblBalance.setText("Balance = RS "+rentDto.getBalance());
            lblCarDetails.setVisible(true);
            lblCustomerDetails.setVisible(true);
            anchorPaneTotal.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    void clear(){
        txtRentId.setText("");
        txtCustId.setText("");
        txtCarId.setText("");
        txtPricePerDay.setText("");
        txtDeposit.setText("");
        txtAdvancedPay.setText("");

        datePickerFromDate.setValue(null);
        datePickerToDate.setValue(null);
        anchorPaneTotal.setVisible(false);
        lblDetailsCar.setVisible(false);
        lblDetailsCust.setVisible(false);

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();

    }
}

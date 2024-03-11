package lk.ijse.carrent.layerd.controller;

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
import lk.ijse.carrent.layerd.dto.CustomerDto;
import lk.ijse.carrent.layerd.dto.tm.CustomerTm;
import lk.ijse.carrent.layerd.service.ServiceFactory;
import lk.ijse.carrent.layerd.service.custom.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDob;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;


    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableView<CustomerTm> tblGetAllCustomer;

    @FXML
    private AnchorPane anchorPaneTbl;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblShowDetails;

    static List<String> upMobiles = new ArrayList<>();
    @FXML
    private ComboBox<String> cmbMobiles;
    @FXML
    private AnchorPane anchorPaneUpdateNumber;
    @FXML
    private TextField txtUpdateNumber;
    @FXML
    private Label lblUpdateBack;


    static String userName;

    static List<String> mobiles = new ArrayList<>();
    @FXML
    private Label labelUpdateNumber;

    CustomerService customerService = (CustomerService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CUSTOMER);

    public void initialize() {

        upMobiles.clear();
        mobiles.clear();
        setValueFactory();
        getAllTable();
        lblBack.setVisible(false);
        backTbl();
        lblUpdateBack.setVisible(false);
        anchorPaneUpdateNumber.setVisible(false);
        txtId.setText("CUI0");

    }

    private void backTbl() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.8),anchorPaneTbl);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        lblShowDetails.setVisible(true);
        lblBack.setVisible(false);

    }

    @FXML
    void showAllDetailsOnAction(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2),anchorPaneTbl);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        lblShowDetails.setVisible(false);
        lblBack.setVisible(true);

    }

    @FXML
    void lblBackOnAction(MouseEvent event) {

        backTbl();

    }

    private void getAllTable() {
        try {
            List<CustomerDto> customerDtos =  customerService.getAllCustomer();
            setCustomerTable(customerDtos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCustomerTable(List<CustomerDto> customerDtos) {
        ObservableList<CustomerTm> obl = FXCollections.observableArrayList();

        for (CustomerDto dto:customerDtos
             ) {
            var tm = new CustomerTm(dto.getId(), dto.getNic(), dto.getName(), dto.getAddress(), dto.getDob());
            obl.add(tm);
        }
        tblGetAllCustomer.setItems(obl);
    }

    private void setValueFactory() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));

    }



    public void runUserId(String id){


        userName = id;
    }

    @FXML
    void btnAddMobileOnAction(ActionEvent event) {

        mobiles.add(txtMobile.getText());
        txtMobile.setText("");

    }

    @FXML
    void btnAddCusOnAction(ActionEvent event) {

        CustomerDto customerDto = new CustomerDto(txtId.getText(),txtNic.getText(),txtName.getText(),txtAddress.getText(),txtDob.getText(),userName,mobiles);
        try {
            String result = customerService.addCustomer(customerDto);
            mobiles.clear();
            getAllTable();
            clear();
            new Alert(Alert.AlertType.CONFIRMATION,result).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnDeleteCusOnAction(ActionEvent event) {
        CustomerDto customerDto = new CustomerDto(txtId.getText(),
                txtNic.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtDob.getText(),
                userName,upMobiles);

        try {
            String result =  customerService.delete(customerDto);
            new Alert(Alert.AlertType.CONFIRMATION,result).show();
            getAllTable();
            upMobiles.clear();
            clear();
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateCusOnAction(ActionEvent event) {

        CustomerDto customerDto = new CustomerDto(txtId.getText(),
                txtNic.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtDob.getText(),
                userName,upMobiles);

        try {
            String result = customerService.updateCustomer(customerDto);
            upMobiles.clear();
            new Alert(Alert.AlertType.CONFIRMATION,result).show();
            getAllTable();
            clear();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }

    @FXML
    void getValues(MouseEvent event) {

        Integer index = tblGetAllCustomer.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            new Alert(Alert.AlertType.ERROR, "Error").show();
        } else {

            try {
                CustomerDto customerDto = customerService.searchCustomer(colId.getCellData(index).toString());
                txtId.setText(customerDto.getId());
                txtAddress.setText(customerDto.getAddress());
                txtDob.setText(customerDto.getDob());
                txtName.setText(customerDto.getName());
                txtNic.setText(customerDto.getNic());
                upMobiles.clear();
                mobiles.clear();

                for (String mobiles : customerDto.getMobil()
                ) {
                    upMobiles.add(mobiles);
                }

                ObservableList<String> obl = FXCollections.observableArrayList();
                for (String mobil : customerDto.getMobil()
                ) {
                    obl.add(mobil);
                }

                cmbMobiles.setItems(obl);

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }


        }

    }

    @FXML
    void cmbUpdateNumberOnAction(ActionEvent event) {

        txtUpdateNumber.setText(cmbMobiles.getValue());

    }

    @FXML
    void btnUpdateAddMobileOnAction(ActionEvent event) {

        Boolean isDuplicate = true;

        for (int i = 0; i < upMobiles.size(); i++) {
            if (upMobiles.get(i).equals(txtUpdateNumber.getText())) {

                isDuplicate = false;
                break;

            }

        }

        if (!isDuplicate) {

            new Alert(Alert.AlertType.INFORMATION, "This number is already executed ").show();
        } else {
            upMobiles.add(txtUpdateNumber.getText());
        }

        txtUpdateNumber.setText("");



    }

    @FXML
    void btnUpdateMobileOnAction(ActionEvent event) {

        int index = -1;

        for (int i = 0; i < upMobiles.size(); i++) {

            if (upMobiles.get(i).equals(cmbMobiles.getValue())) {
                index = i;
                break;
            }

        }

        if (index != -1) {

            Boolean isDuplicate = true;

            for (int i = 0; i < upMobiles.size(); i++) {
                if (upMobiles.get(i).equals(txtUpdateNumber.getText())) {

                    isDuplicate = false;
                    break;

                }


            }

            if (!isDuplicate) {

                new Alert(Alert.AlertType.INFORMATION, "This number is already executed ").show();
            } else {
                upMobiles.set(index, txtUpdateNumber.getText());
                new Alert(Alert.AlertType.CONFIRMATION, "Number Update Successful").show();
            }

        } else {
            new Alert(Alert.AlertType.INFORMATION, "this number is not listed").show();
        }

        txtUpdateNumber.setText("");




    }

    @FXML
    void btnDeleteMobilOnAction(ActionEvent event) {


        int index = -1;

        for (int i = 0; i < upMobiles.size(); i++) {

            if (upMobiles.get(i).equals(cmbMobiles.getValue())) {
                index = i;
                break;
            }

        }

        if (index != -1) {

            upMobiles.remove(index);
            new Alert(Alert.AlertType.CONFIRMATION, " Number Delete Successful").show();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "this number is not listed").show();
        }



        txtUpdateNumber.setText("");

    }

    @FXML
    void lblUpdateMoblePaneOnAction(MouseEvent event) {

        anchorPaneUpdateNumber.setVisible(true);
        lblUpdateBack.setVisible(true);
        labelUpdateNumber.setVisible(false);

    }

    @FXML
    void lblBackUpdateNumberPaneOnAction(MouseEvent event) {

        anchorPaneUpdateNumber.setVisible(false);
        lblUpdateBack.setVisible(false);
        labelUpdateNumber.setVisible(true);
    }

    void clear(){

        txtNic.setText("");
        txtId.setText("");
        txtName.setText("");
        txtDob.setText("");
        txtAddress.setText("");
        txtMobile.setText("");
        txtUpdateNumber.setText("");

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

        clear();
        mobiles.clear();
        upMobiles.clear();

    }




}




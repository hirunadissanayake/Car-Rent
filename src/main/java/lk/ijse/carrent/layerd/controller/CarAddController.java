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
import lk.ijse.carrent.layerd.dto.CarCategoryDto;
import lk.ijse.carrent.layerd.dto.CarDetailsDto;
import lk.ijse.carrent.layerd.dto.tm.CarDetailsTm;
import lk.ijse.carrent.layerd.service.ServiceFactory;
import lk.ijse.carrent.layerd.service.custom.CarCategoryService;
import lk.ijse.carrent.layerd.service.custom.CarDetailsSrevice;

import java.util.ArrayList;
import java.util.List;

public class CarAddController {

    @FXML
    private Label lblBack;


    @FXML
    private AnchorPane anchorPaneTbl;
    @FXML
    private Label lblShowDetails;

    @FXML
    private ComboBox<String> cmbCarCategory;

    @FXML
    private TextField txtCarCategoryId;

    static String userName;
    CarDetailsSrevice carDetailsSrevice = (CarDetailsSrevice) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CARDETAILS);
    @FXML
    private TextField txtBrand;
    @FXML
    private TextField txtCarId;
    @FXML
    private TextField txtModel;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtPricePerDay;
    @FXML
    private TextField txtYer;
    @FXML
    private TableColumn<?, ?> colBrand;
    @FXML
    private TableColumn<?, ?> colCarCategoryId;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colModel;
    @FXML
    private TableColumn<?, ?> colNumber;
    CarCategoryService carCategoryService = (CarCategoryService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CARCATEGORY);
    @FXML
    private TableColumn<?, ?> colPriceperDay;
    @FXML
    private TableView<CarDetailsTm> tblCar;

    public void initialize() {

        setValueFactory();
        txtCarId.setText("CI0");
        getCmb();
        getAllTbl();
        lblBack.setVisible(false);
        backTbl();

    }
    private void getAllTbl(){
        List<CarDetailsDto> dtos = new ArrayList<>();
        try {
            dtos = carDetailsSrevice.getAll();
            setCarDetails(dtos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void getCmb(){
        List<CarCategoryDto> categoryDtos = new ArrayList<>();
        try {
            categoryDtos = carCategoryService.getAll();
            setCarCategoryName(categoryDtos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void setValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCarCategoryId.setCellValueFactory(new PropertyValueFactory<>("carCategoryId"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colPriceperDay.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));
    }

    private void setCarDetails(List<CarDetailsDto> dtos) {
        ObservableList<CarDetailsTm> obl = FXCollections.observableArrayList();
        for (CarDetailsDto dto : dtos
        ) {
            var tm = new CarDetailsTm(dto.getId(), dto.getCarCategoryId(), dto.getBrand(), dto.getModel(), dto.getVehicleNumber(), dto.getPricePerDay());
            obl.add(tm);
        }
        tblCar.setItems(obl);
    }

    private void setCarCategoryName(List<CarCategoryDto> categoryDtos) {
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (CarCategoryDto dto : categoryDtos
        ) {
            obList.add(dto.getName());
        }
        cmbCarCategory.setItems(obList);

    }

    @FXML
    void cmbCarCategoryOnAction(ActionEvent event) {
        String CarCategoryName = cmbCarCategory.getValue();
        try {
            CarCategoryDto carCategoryDto = carCategoryService.search(CarCategoryName);
            txtCarCategoryId.setText(carCategoryDto.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void runUserId(String id) {


        userName = id;
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String carCategoryName = cmbCarCategory.getValue();
        try {


            CarDetailsDto carDetailsDto = new CarDetailsDto(txtCarId.getText()
                    , txtCarCategoryId.getText()
                    , userName,
                    txtBrand.getText(),
                    txtModel.getText(),
                    Integer.parseInt(txtYer.getText()),
                    txtNumber.getText(),
                    Double.parseDouble(txtPricePerDay.getText()),
                    carCategoryName);

            try {
                String result = carDetailsSrevice.addCar(carDetailsDto);
                getAllTbl();
                clear();

                new Alert(Alert.AlertType.INFORMATION, result).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }catch (Exception ex){
            new Alert(Alert.AlertType.ERROR,"Wrong values").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {


        String carCategoryName = cmbCarCategory.getValue();
        try {


            CarDetailsDto carDetailsDto = new CarDetailsDto(txtCarId.getText()
                    , txtCarCategoryId.getText()
                    , userName,
                    txtBrand.getText(),
                    txtModel.getText(),
                    Integer.parseInt(txtYer.getText()),
                    txtNumber.getText(),
                    Double.parseDouble(txtPricePerDay.getText()),
                    carCategoryName);

            try {
                String result = carDetailsSrevice.update(carDetailsDto);
                getAllTbl();
                clear();

                new Alert(Alert.AlertType.INFORMATION, result).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }catch (Exception ex){
            new Alert(Alert.AlertType.ERROR,"Wrong values").show();
        }

    }

    public void getValue(MouseEvent mouseEvent) throws Exception {
        Integer index = tblCar.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            new Alert(Alert.AlertType.ERROR, "Error").show();
        } else {


            CarDetailsDto carDetailsDto = carDetailsSrevice.search(colId.getCellData(index).toString());
            txtCarId.setText(carDetailsDto.getId());
            txtCarCategoryId.setText(carDetailsDto.getCarCategoryId());
            txtBrand.setText(carDetailsDto.getBrand());
            txtModel.setText(carDetailsDto.getModel());
            txtYer.setText(String.valueOf(carDetailsDto.getYear()));
            txtNumber.setText(carDetailsDto.getVehicleNumber());
            txtPricePerDay.setText(String.valueOf(carDetailsDto.getPricePerDay()));

        }
    }

    void clear() {

        txtCarId.setText("");
        txtBrand.setText("");
        txtModel.setText("");
        txtYer.setText("");
        txtNumber.setText("");
        txtPricePerDay.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {


        try {
            String result = carDetailsSrevice.delete(txtCarId.getText());
            clear();
            initialize();
            new Alert(Alert.AlertType.CONFIRMATION, result).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();

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

    void backTbl(){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2),anchorPaneTbl);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        lblShowDetails.setVisible(true);
        lblBack.setVisible(false);

    }


    @FXML
    void backOnAction(MouseEvent event) {
        backTbl();

    }



}

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
import lk.ijse.carrent.layerd.dto.tm.GetCarDetailsTm;
import lk.ijse.carrent.layerd.service.ServiceFactory;
import lk.ijse.carrent.layerd.service.custom.CarCategoryService;
import lk.ijse.carrent.layerd.service.custom.CarDetailsSrevice;

import java.util.ArrayList;
import java.util.List;


public class GetCarDetailsController {


    @FXML
    private ComboBox<String> cmbCarCategory;

    @FXML
    private ComboBox<String> cmbCarModel;

    @FXML
    private TextField txtCarCategoryId;

    @FXML
    private ComboBox<String> cmbCarModel1;

    @FXML
    private TableColumn<?, ?> colCarId;

    @FXML
    private TableColumn<?, ?> colNumber;

    @FXML
    private TableColumn<?, ?> colPricePerDay;

    @FXML
    private TableColumn<?, ?> colYer;

    @FXML
    private TableView<GetCarDetailsTm> tblCar;

    @FXML
    private AnchorPane anchorPaneCarDetailsAll;


    @FXML
    private Label lblCarBrand;

    @FXML
    private Label lblCarCategoryId;

    @FXML
    private Label lblCarCategoryName;

    @FXML
    private Label lblCarId;

    @FXML
    private Label lblCarModel;

    @FXML
    private Label lblCarNumber;

    @FXML
    private Label lblCarYear;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblUserName;


    CarDetailsSrevice carDetailsSrevice = (CarDetailsSrevice) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CARDETAILS);
    CarCategoryService carCategoryService = (CarCategoryService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CARCATEGORY);

    public void initialize() {

        getAllCarCategory();
        setValueFactory();
        anchorPaneCarDetailsAll.setVisible(false);

    }
    private void getAllCarCategory() {
        List<CarCategoryDto> categoryDtos = new ArrayList<>();
        try {
            categoryDtos = carCategoryService.getAll();
            setCarCategoryName(categoryDtos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
            getCarBrand(carCategoryDto.getId());


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }


    }

    void getCarBrand(String id){



        try {
            List<String> carModel =carDetailsSrevice.getBrand(id);
            ObservableList<String> obl = FXCollections.observableArrayList();

            for (String brand:carModel
                 ) {
                obl.add(brand);
            }
            cmbCarModel.setItems(obl);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void cmbCarBrandOnAction(ActionEvent event) {
        String brand = cmbCarModel.getValue();
        String carCategoryId = txtCarCategoryId.getText();



        CarDetailsDto carDetailsDto = new CarDetailsDto();
        carDetailsDto.setBrand(brand);
        carDetailsDto.setCarCategoryId(carCategoryId);

        try {
            List<String> carModel =carDetailsSrevice.getCarModel(carDetailsDto.getCarCategoryId(),carDetailsDto.getBrand());
            setCarMode(carModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void setCarMode(List<String> carModel) {
        ObservableList<String> obl = FXCollections.observableArrayList();
        for (String model:carModel
             ) {
            obl.add(model);
        }
        cmbCarModel1.setItems(obl);
    }


    @FXML
    void cmbCarModelOnAction(ActionEvent event) {

        String carCategoryId = txtCarCategoryId.getText();
        String carBrand = cmbCarModel.getValue();
        String carModel = cmbCarModel1.getValue();

        CarDetailsDto carDetailsDto = new CarDetailsDto(carCategoryId,carBrand,carModel);
        try {
            List<CarDetailsDto>carDetailsDtos = carDetailsSrevice.getCarAll(carDetailsDto);
            getCarDetails(carDetailsDtos);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }


    }

    private void getCarDetails(List<CarDetailsDto> carDetailsDtos) {
        ObservableList<GetCarDetailsTm> obl = FXCollections.observableArrayList();
        for (CarDetailsDto dto:carDetailsDtos
             ) {

            var tm = new GetCarDetailsTm(dto.getId(),dto.getYear(),dto.getVehicleNumber(),dto.getPricePerDay());
            obl.add(tm);
        }
        tblCar.setItems(obl);
    }

    private void setValueFactory() {

        colCarId.setCellValueFactory(new PropertyValueFactory<>("id"));

        colYer.setCellValueFactory(new PropertyValueFactory<>("year"));

        colNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));

        colPricePerDay.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));


    }


    @FXML
    void btnTableOnAction(MouseEvent event) throws Exception {

        Integer index = tblCar.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            new Alert(Alert.AlertType.ERROR, "Error").show();
        } else {


            CarDetailsDto carDetailsDto = carDetailsSrevice.search(colCarId.getCellData(index).toString());
            lblCarCategoryId.setText(carDetailsDto.getCarCategoryId());
            lblCarCategoryName.setText(carDetailsDto.getCarCategoryName());
            lblCarId.setText(carDetailsDto.getId());
            lblCarBrand.setText(carDetailsDto.getBrand());
            lblCarModel.setText(carDetailsDto.getModel());
            lblCarNumber.setText(carDetailsDto.getVehicleNumber());
            lblCarYear.setText(String.valueOf(carDetailsDto.getYear()));
            lblPrice.setText("Rs "+String.valueOf(carDetailsDto.getPricePerDay()));
            lblUserName.setText(carDetailsDto.getUserName());

            getPane();

        }

    }


    void getPane() {
        anchorPaneCarDetailsAll.setVisible(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2),anchorPaneCarDetailsAll);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();


    }

    @FXML
    void lblBackOnAction(MouseEvent event) {

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2),anchorPaneCarDetailsAll);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

    }

}

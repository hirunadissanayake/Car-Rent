package lk.ijse.carrent.layerd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.carrent.layerd.dto.CarCategoryDto;
import lk.ijse.carrent.layerd.dto.tm.CarCategoryTm;
import lk.ijse.carrent.layerd.service.ServiceFactory;
import lk.ijse.carrent.layerd.service.custom.CarCategoryService;

import java.util.List;

public class CarCategoryController {
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;
    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableView<CarCategoryTm> tblCarCategory;


    static String userName;
    CarCategoryService carCategoryService = (CarCategoryService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CARCATEGORY);

    public void initialize() {

        txtId.setText("CC0");

        setValueFactory();
        try {
            List<CarCategoryDto> categoryDtos = carCategoryService.getAll();
            setTableData(categoryDtos);





        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }



    private void setTableData(List<CarCategoryDto> categoryDtos) {

        ObservableList<CarCategoryTm> categoryTms = FXCollections.observableArrayList();
        for (CarCategoryDto dto:categoryDtos
             ) {

            var tm = new CarCategoryTm(dto.getId(), dto.getName(), dto.getUserid());

                    categoryTms.add(tm);
        }
        tblCarCategory.setItems(categoryTms);
    }

    private void setValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userName"));
    }

    public void runUserId(String id){


        userName = id;
    }
    @FXML
    void btnAddOnAction(ActionEvent event) {





        CarCategoryDto carCategoryDto = new CarCategoryDto(txtId.getText(),txtName.getText(),userName);

        try {
            String result = carCategoryService.addCarCategory(carCategoryDto);
            new Alert(Alert.AlertType.INFORMATION,result).show();
            clear();
            initialize();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
    private void clear(){
        txtName.setText("");
        txtId.setText("");
    }

    @FXML
    void getValue(MouseEvent event) {

        Integer index = tblCarCategory.getSelectionModel().getSelectedIndex();

        if(index <= -1){
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }else {

            txtId.setText(colId.getCellData(index).toString());
            txtName.setText(colName.getCellData(index).toString());
        }

    }


    @FXML
    void btnClear(ActionEvent event) {
        clear();
    }

    @FXML
    void btnUpdateCarCategoryOnAction(ActionEvent event) {


        CarCategoryDto carCategoryDto = new CarCategoryDto(txtId.getText(),txtName.getText(),userName);

        try {
          String result =  carCategoryService.update(carCategoryDto);
          new Alert(Alert.AlertType.CONFIRMATION,result).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        clear();
        initialize();

    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        CarCategoryDto carCategoryDto = new CarCategoryDto(txtId.getText());


        try {
            String result = carCategoryService.delete(carCategoryDto);
            clear();
            initialize();
            new Alert(Alert.AlertType.CONFIRMATION,result).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
}

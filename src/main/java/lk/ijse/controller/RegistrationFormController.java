package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ProgramBO;
import lk.ijse.bo.custom.RegisterBO;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dto.ProgramDTO;
import lk.ijse.dto.RegisterDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.view.CartTm;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RegistrationFormController {
    public TextField txtStudentId;
    public TextField txtStudentName;
    public AnchorPane imageRootNode;
    public ImageView imageView;
    public TextField txtStudentTel;
    public Label lblDate;
    public Label lblTime;
    public JFXComboBox<String> cmbProgramNames;
    public Label lblRegisterId;
    public TextField txtProgramId;
    public Label lblProgramFee;
    public TextField txtPayment;
    public TableColumn<?,?> colProgramId;
    public TableColumn<?,?> colProgramName;
    public TableColumn<?,?> colProgramFee;
    public TableColumn<?,?> colAmount;
    public TableColumn<?,?> colPay;
    public TableView<CartTm> tblRegistrationCart;
    public DatePicker txtDate;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENTBO);
    ProgramBO programBO = (ProgramBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAMBO);
    RegisterBO registerBO = (RegisterBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REJISTERBO);
    ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        getProgramNames();
        setCellValueFactory();
        getCurrentRegisterId();
    }

    private void setCellValueFactory() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProgramFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colPay.setCellValueFactory(new PropertyValueFactory<>("pay"));
    }

    private void getCurrentRegisterId() {
        try {
            int currentId = registerBO.getCurrentRegisterId();
            String nextOrderId = generateNextOrderId(currentId);
            lblRegisterId.setText(nextOrderId);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private String generateNextOrderId(int currentId) {
        if(currentId != 0) {
            int idNum = currentId;
            System.out.println(idNum);
            return "Reg-0" + ++idNum;
        }
        return "Reg-01";
    }

    private void getProgramNames() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<ProgramDTO> programList = programBO.getAllProgram();
            List<String> programNameList = new ArrayList<>();

            for (ProgramDTO programDTO : programList){
                programNameList.add(programDTO.getProgramName());
            }
            for (String name: programNameList) {
                obList.add(name);
            }
            cmbProgramNames.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void txtStudentIdOnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        try {
            StudentDTO student = studentBO.searchStudent(id);
            if (student != null) {
                txtStudentName.setText(student.getStudentFullName());
                Image image = new Image(student.getStudentPath());
                imageView.setImage(image);
                txtStudentTel.setText(student.getStudentContact());


            }else{
                cmbProgramNames.requestFocus();
                new Alert(Alert.AlertType.INFORMATION, "student not found!").show();
            }


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void txtCourseDurationOnAction(ActionEvent actionEvent) {

    }

    public void txtProgramIdOnAction(ActionEvent actionEvent) {

    }

    public void txtProgramNameOnAction(ActionEvent actionEvent) {

    }

    public void cmbProgramNameOnAction(ActionEvent actionEvent) {
        String name = cmbProgramNames.getValue();
        try {
            ProgramDTO program = programBO.searchProgramByName(name);
            if (program != null) {
                txtProgramId.setText(program.getProgramId());
                lblProgramFee.setText(String.valueOf(program.getProgramFee()));
                txtPayment.requestFocus();
            }

            txtPayment.requestFocus();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String pId = txtProgramId.getText();
        String pName = cmbProgramNames.getValue();
        double fee = Double.parseDouble(lblProgramFee.getText());
        double payment = Double.parseDouble(txtPayment.getText());
        double pay = fee - payment;


        CartTm tm = new CartTm(pId, pName, fee, payment, pay);
        obList.add(tm);
        tblRegistrationCart.setItems(obList);
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        List<RegisterDTO> registerDTOList = new ArrayList<>();

        for (CartTm cartTm : tblRegistrationCart.getItems()) {
            RegisterDTO registerDTO = new RegisterDTO(
                    //String.valueOf((Integer.parseInt(registrationBO.getCurrentRegistrationId())+1)),           // Registration ID
                    txtStudentId.getText(),            // Student ID
                    cartTm.getId(),                     // Program ID
                    txtDate.getValue(),
                    cartTm.getPayment(),        // Upfront Payment
                    cartTm.getPay()// Date (formatted as String)
            );

            // Add each RegistrationDTO to the list
            registerDTOList.add(registerDTO);
        }

        registerBO.placeRegister(registerDTOList);
    }
}

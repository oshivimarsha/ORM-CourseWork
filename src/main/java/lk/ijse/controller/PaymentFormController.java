package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.bo.custom.ProgramBO;
import lk.ijse.bo.custom.RegisterBO;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dto.ProgramDTO;
import lk.ijse.dto.RegisterDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentFormController {
    public TextField txtStudentId;
    public TextField txtStudentName;
    public TextField txtStudentTel;
    public Label lblPaymentId;
    public DatePicker datePicker;
    public JFXComboBox<String> cmbProgramName;
    public TextField txtProgramFee;
    public TextField txtUpFrontPayment;
    public TextField txtTotalPaid;
    public TextField txtBalance;
    public TextField txtPayingAmount;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENTBO);
    ProgramBO programBO = (ProgramBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAMBO);
    RegisterBO registerBO = (RegisterBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REJISTERBO);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENTBO);


    public void initialize() {
        getProgramNames();
    }
    public void txtStudentIdOnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        try {
            StudentDTO student = studentBO.searchStudent(id);
            if (student != null) {
                txtStudentName.setText(student.getStudentFullName());
                txtStudentTel.setText(student.getStudentContact());


            }else{
                cmbProgramName.requestFocus();
                new Alert(Alert.AlertType.INFORMATION, "student not found!").show();
            }


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnPayOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String studentId = txtStudentId.getText();
        String payingamount = txtPayingAmount.getText();

        Student student = new Student();
        student.setStudentId(studentId);

        Payment payment = new Payment();
        payment.setUpfrontpayment(Double.parseDouble(payingamount));
        payment.setStudent(student);

        boolean isSaved = paymentBO.savePayment(payment);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Payment Saved", ButtonType.OK).show();
           // clearField();
        }else {
            new Alert(Alert.AlertType.ERROR, "Payment Not Saved", ButtonType.OK).show();
        }
    }

    public void getDateOnAction(ActionEvent actionEvent) {

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
            cmbProgramName.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void cmbProgramNameOnAction(ActionEvent actionEvent) {
        String name = cmbProgramName.getValue();
        try {
            ProgramDTO program = programBO.searchProgramByName(name);
            RegisterDTO register = registerBO.searchProgramByName(name);
            if (program != null) {
                txtProgramFee.setText(program.getProgramFee());
               // txtUpFrontPayment.setText(String.valueOf(register.getPayment()));
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}

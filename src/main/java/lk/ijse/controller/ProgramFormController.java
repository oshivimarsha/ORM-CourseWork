package lk.ijse.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ProgramBO;
import lk.ijse.dto.ProgramDTO;
import lk.ijse.view.ProgramTm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProgramFormController {
    @FXML
    public TableView<ProgramTm> tblProgramCart;
    @FXML
    public TableColumn<?, ?> colProgramId;
    @FXML
    public TableColumn<?, ?> colProgramName;
    @FXML
    public TableColumn<?, ?> colProgramDuration;
    @FXML
    public TableColumn<?, ?> colProgramFee;

    @FXML
    private TextField txtProgramDuration;

    @FXML
    private TextField txtProgramFee;

    @FXML
    private TextField txtProgramId;

    @FXML
    private TextField txtProgramName;


    ProgramBO programBO = (ProgramBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAMBO);

    public void initialize() {
        loadAllProgram();
        setCellValueFactory();
        getCurrentId();

    }

    private void loadAllProgram() {
        ObservableList<ProgramTm> obList = FXCollections.observableArrayList();

        try {
            List<ProgramDTO> programList = programBO.getAllProgram();
            for (ProgramDTO program : programList) {
                ProgramTm tm = new ProgramTm(
                        program.getProgramId(),
                        program.getProgramName(),
                        program.getProgramDuration(),
                        program.getProgramFee()
                );

                obList.add(tm);
            }

            tblProgramCart.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colProgramDuration.setCellValueFactory(new PropertyValueFactory<>("programDuration"));
        colProgramFee.setCellValueFactory(new PropertyValueFactory<>("programFee"));
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        System.out.println("1");
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Save Course?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
        String pId = txtProgramId.getText();
        String pName = txtProgramName.getText();
        String pDuration = txtProgramDuration.getText();
        String pFee = txtProgramFee.getText();

            System.out.println("2");


            ProgramDTO program = new ProgramDTO(pId, pName, pDuration, pFee);
            System.out.println("3");

            try {
                System.out.println("10");
                boolean isSaved = programBO.saveProgram(program);
                System.out.println("4");
                if (isSaved) {
                    System.out.println("5");
                    new Alert(Alert.AlertType.CONFIRMATION, "program saved!").show();
                    System.out.println("6");
                    clearFields();
                    initialize();
                    System.out.println("7");
                }
            } catch (Exception e) {
                System.out.println("8");
                new Alert(Alert.AlertType.CONFIRMATION, "Wrong Input!").show();
                System.out.println("9");
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Update Program?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            String pId = txtProgramId.getText();
            String pName = txtProgramName.getText();
            String pDuration = txtProgramDuration.getText();
            String pFee = txtProgramFee.getText();

            ProgramDTO program = new ProgramDTO(pId, pName, pDuration, pFee);

            try {
                boolean isUpdated = programBO.updateProgram(program);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "program updated!").show();
                    clearFields();
                    initialize();
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "The data you entered is incorrect").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public void clearFields() {
        txtProgramId.setText("");
        txtProgramName.setText("");
        txtProgramDuration.setText("");
        txtProgramFee.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete program?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            String id = txtProgramId.getText();

            try {
                boolean isDeleted = programBO.deleteProgram(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "program deleted!").show();
                    clearFields();
                    initialize();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private void getCurrentId() {
        try {
            String currentId = programBO.generateNewID();
            String nextProgramId = generateNextProgramId(currentId);
            txtProgramId.setText(nextProgramId);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private String generateNextProgramId(String currentId) {
        if(currentId != null) {
            /*String[] split = currentId.split("S-");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);*/
            int idNum = Integer.parseInt(currentId);
            return "Pro-0" + ++idNum;
        }
        return "Pro-01";
    }

    @FXML
    void txtProgramDurationOnAction(ActionEvent event) {

    }

    @FXML
    void txtProgramFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtProgramIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtProgramNameOnAction(ActionEvent event) {

    }
}

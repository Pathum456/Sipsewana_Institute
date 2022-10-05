package controller;

import bo.BOFactory;
import bo.BOType;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dao.custom.StudentDAO;
import dao.custom.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.tm.StudentTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ManageStudentFormController {
    public AnchorPane ManageStudentContext;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtNic;
    public JFXTextField txtSchool;
    public JFXTextField txtMobile;
    public Button btnAdd;
    public TableView <StudentTm>tblStudent;
    public DatePicker dateBirthPic;
    public TableColumn colDelete;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colNic;
    public TableColumn colScl;
    public TableColumn colMobile;
    public TableColumn colBirth;
    public Button btnAdd1;

    StudentBOImpl studentBOImpl = BOFactory.getInstance().getBO(BOType.STUDENT);


    public void initialize(){
        findAll();
        tableListener();
        setCellValueFactory();
    }

    private void tableListener(){
        tblStudent.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData(newValue);
                });
    }

    private void setData(StudentTm tm) {
        try {
            txtId.setText(tm.getStID());
            txtName.setText(tm.getFullName());
            txtAddress.setText(tm.getAddress());
            txtNic.setText(tm.getNIC());
            txtSchool.setText(tm.getSchool());
            txtMobile.setText(tm.getMobile());
           // dateBirthPic.toString(tm.getBirthday());
        }catch (Exception e){

        }
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory("stID"));
        colName.setCellValueFactory(new PropertyValueFactory("fullName"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colNic.setCellValueFactory(new PropertyValueFactory("NIC"));
        colScl.setCellValueFactory(new PropertyValueFactory("school"));
        colMobile.setCellValueFactory(new PropertyValueFactory("mobile"));
        colBirth.setCellValueFactory(new PropertyValueFactory("birthday"));
        colDelete.setCellValueFactory(new PropertyValueFactory("btn"));
    }

    public void findAll(){
        try {
            ObservableList<StudentTm> tmList = FXCollections.observableArrayList();
            List<StudentDTO> all = studentBOImpl.findAll();
            for(StudentDTO dto : all){
                Button btn = new Button("Delete");
                StudentTm tm = new StudentTm(
                        dto.getStID(),
                        dto.getFullName(),
                        dto.getAddress(),
                        dto.getNIC(),
                        dto.getSchool(),
                        dto.getMobile(),
                        dto.getBirthday(),
                        btn
                );
                tmList.add(tm);
                btn.setOnAction((e) -> {
                    try {

                        ButtonType ok = new ButtonType("OK",
                                ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            if (studentBOImpl.delete(tm.getStID())) {
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Deleted", ButtonType.OK).show();
                                findAll();
                                return;
                            }
                            new Alert(Alert.AlertType.WARNING,
                                    "Try Again", ButtonType.OK).show();
                        } else {

                        }


                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            }
            tblStudent.setItems(tmList);
        } catch (Exception e) {
        }
    }


    public void AddStudentOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String nic=txtNic.getText();
        String scl=txtSchool.getText();
        String mobile=txtMobile.getText();
        LocalDate date=dateBirthPic.getValue();

        try {
            if (studentBOImpl.add(new StudentDTO(
                    id,
                    name,
                    address,
                    nic,
                    scl,
                    mobile,
                    date.toString()
            )));{
                new Alert(Alert.AlertType.CONFIRMATION, "Do you Save it?").showAndWait();
                findAll();
                txtId.clear();
                txtName.clear();
                txtAddress.clear();
                txtNic.clear();
                txtSchool.clear();
                txtMobile.clear();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, " try again ").showAndWait();
        }

    }


    public void updateOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String nic=txtNic.getText();
        String scl=txtSchool.getText();
        String mobile=txtMobile.getText();
        LocalDate date=dateBirthPic.getValue();

        try {
            if(studentBOImpl.update(new StudentDTO(
                    id,
                    name,
                    address,
                    nic,
                    scl,
                    mobile,
                    date.toString()
            ))){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").showAndWait();
                findAll();
                txtId.clear();
                txtName.clear();
                txtAddress.clear();
                txtNic.clear();
                txtSchool.clear();
                txtMobile.clear();
            }else {
                new Alert(Alert.AlertType.ERROR, "Something Happened").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Happened").show();
        }
    }
}

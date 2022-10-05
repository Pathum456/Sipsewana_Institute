package controller;

import bo.BOFactory;
import bo.BOType;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import entity.tm.StudentTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Optional;

public class AllStudentDetailFormController {
    public AnchorPane AllStudentDetailContext;
    public JFXTextField txtName;
    public TableView <StudentTm>tblStudent;
    public TableColumn colId;
    public TableColumn ColFullName;
    public TableColumn colAddress;
    public TableColumn colNic;
    public TableColumn colMobile;
    public TableColumn colScl;
    public TableColumn colBirthday;

    StudentBOImpl studentBOImpl = BOFactory.getInstance().getBO(BOType.STUDENT);


    public void initialize(){
        findAll();
        setCellValueFactory();
    }


    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory("stID"));
        ColFullName.setCellValueFactory(new PropertyValueFactory("fullName"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colNic.setCellValueFactory(new PropertyValueFactory("NIC"));
        colScl.setCellValueFactory(new PropertyValueFactory("school"));
        colMobile.setCellValueFactory(new PropertyValueFactory("mobile"));
        colBirthday.setCellValueFactory(new PropertyValueFactory("birthday"));

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


}

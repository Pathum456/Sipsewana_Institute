package controller;

import bo.BOFactory;
import bo.BOType;
import bo.custom.impl.CourseBOImpl;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDTO;
import dto.StudentDTO;
import entity.tm.CourseTm;
import entity.tm.StudentTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Optional;

public class ManageCourseFormController {
    public AnchorPane ManageCourseContext;
    public JFXTextField txtId;
    public JFXTextField txtProgram;
    public JFXTextField txtDuration;
    public JFXTextField txtFees;
    public Button btnAdd;
    public TableView <CourseTm>tblContext;
    public Button btnAdd1;
    public TableColumn colId;
    public TableColumn colProgram;
    public TableColumn colDuration;
    public TableColumn colFees;
    public TableColumn colDelete;

    CourseBOImpl courseBOImpl = BOFactory.getInstance().getBO(BOType.COURSE);


    public void initialize(){
        findAll();
        tableListener();
        setCellValueFactory();
    }

    private void tableListener(){
        tblContext.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData(newValue);
                });
    }

    private void setData(CourseTm tm) {
        try {
            txtId.setText(tm.getCourseID());
            txtProgram.setText(tm.getProgram());
            txtDuration.setText(tm.getDuration());
            txtFees.setText(String.valueOf(tm.getFees()));
        }catch (Exception e){

        }
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory("courseID"));
        colProgram.setCellValueFactory(new PropertyValueFactory("program"));
        colDuration.setCellValueFactory(new PropertyValueFactory("duration"));
        colFees.setCellValueFactory(new PropertyValueFactory("fees"));
        colDelete.setCellValueFactory(new PropertyValueFactory("btn"));
    }

    public void findAll(){
        try {
            ObservableList<CourseTm> tmList = FXCollections.observableArrayList();
            List<CourseDTO> all = courseBOImpl.findAll();
            for(CourseDTO dto : all){
                Button btn = new Button("Delete");
                CourseTm tm = new CourseTm(
                        dto.getCourseID(),
                        dto.getProgram(),
                        dto.getDuration(),
                        dto.getFees(),
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
                            if (courseBOImpl.delete(tm.getCourseID())) {
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
            tblContext.setItems(tmList);
        } catch (Exception e) {
        }
    }



    public void AddCourseOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        String program=txtProgram.getText();
        String duration=txtDuration.getText();
        Double fees= Double.valueOf(txtFees.getText());


        try {
            if (courseBOImpl.add(new CourseDTO(
                    id,
                    program,
                    duration,
                    fees
            )));{
                new Alert(Alert.AlertType.CONFIRMATION, "Do you Save it?").showAndWait();
                findAll();
                txtId.clear();
                txtProgram.clear();
                txtDuration.clear();
                txtFees.clear();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, " try again ").showAndWait();
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        String program=txtProgram.getText();
        String duration=txtDuration.getText();
        Double fees= Double.valueOf(txtFees.getText());

        try {
            if(courseBOImpl.update(new CourseDTO(
                    id,
                    program,
                    duration,
                    fees
            ))){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").showAndWait();
                findAll();
                txtId.clear();
                txtProgram.clear();
                txtDuration.clear();
                txtFees.clear();
            }else {
                new Alert(Alert.AlertType.ERROR, "Something Happened").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Happened").show();
        }
    }
}

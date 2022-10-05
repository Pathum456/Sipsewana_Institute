package controller;

import bo.BOFactory;
import bo.BOType;
import bo.custom.impl.CourseBOImpl;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDTO;
import entity.tm.CourseTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Optional;

public class AllCourseDetailFormController {
    public AnchorPane AllCourseDetailContext;
    public TableView <CourseTm>tblContext;
    public JFXTextField txtSearch;
    public TableColumn colId;
    public TableColumn colProgram;
    public TableColumn colDuration;
    public TableColumn colCourseFess;


    CourseBOImpl courseBOImpl = BOFactory.getInstance().getBO(BOType.COURSE);


    public void initialize(){
        findAll();
        setCellValueFactory();
    }


    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory("courseID"));
        colProgram.setCellValueFactory(new PropertyValueFactory("program"));
        colDuration.setCellValueFactory(new PropertyValueFactory("duration"));
        colCourseFess.setCellValueFactory(new PropertyValueFactory("fees"));
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




}

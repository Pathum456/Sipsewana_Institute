package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class AssistantHomePageFormController {
    public AnchorPane AssistantContext;
    public AnchorPane LoadContext;
    public Button btn4;
    public Button btn3;
    public Button btn2;
    public Button btn1;
    public Label lblDate;
    public Label lblTime;

    public void initialize(){
        loadDateAndTime();
    }

    private void loadDateAndTime() {
        // load Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void BackToOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DashBoardForm.fxml"));
        Parent parent=loader.load();
        Scene scene=new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage stage1= (Stage)AssistantContext.getScene().getWindow();
        stage1.close();
    }

    public void ManageStudentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageStudentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
    }

    public void ManageCourseOnaction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SelectCourseForm.fxml");
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
    }

    public void CourseDetailsOnaction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AllCourseDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
    }
}

package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane DashBoardContext;

    public void LoginHomePageOnACtion(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equalsIgnoreCase("Admin") && txtPassword.getText().equalsIgnoreCase("admin")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/HomePageForm.fxml"));
            Parent parent=loader.load();
            Scene scene=new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage stage1= (Stage)DashBoardContext.getScene().getWindow();
            stage1.close();
        } else if (txtUserName.getText().equalsIgnoreCase("Admin2") && txtPassword.getText().equalsIgnoreCase("1234")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AssistantHomePageForm.fxml"));
            Parent parent=loader.load();
            Scene scene=new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            Stage stage1= (Stage)DashBoardContext.getScene().getWindow();
            stage1.close();
        } else {
            new Alert(Alert.AlertType.WARNING, "Invalid UserName & Password...").show();
        }
    }
}

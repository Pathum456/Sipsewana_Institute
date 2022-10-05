package controller;

import bo.BOFactory;
import bo.BOType;
import bo.custom.CourseBO;
import bo.custom.ManageRegisterBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDTO;
import dto.RegisterDTO;
import dto.StudentDTO;
import entity.Course;
import entity.Student;
import entity.tm.RegisterTm;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class SelectCourseFormController {
    public AnchorPane SelectCourseContext;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtNic;
    public JFXTextField txtSchool;
    public JFXTextField txtMobile;
    public Button btnAdd;
    public TableView <RegisterTm>tblStudent;
    public JFXTextField txtBirthday;
    public JFXComboBox <String>cmbCourseID;
    public JFXTextField txtProgram;
    public JFXTextField txtDuration;
    public JFXTextField txtFees;
    public Button btnAdd1;
    public Button btnAdd2;
    public Text lblRegisterID;
    public JFXComboBox<String> cmbStudentId;
    public Label lblTotal;
    public TableColumn colID;
    public TableColumn colStudentId;
    public TableColumn colCourseID;
    public TableColumn colProgram;
    public TableColumn colDuration;
    public TableColumn colFees;
    private StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);
    private CourseBO courseBO = BOFactory.getInstance().getBO(BOType.COURSE);
    private ManageRegisterBO manageRegisterBO = BOFactory.getInstance().getBO(BOType.REGISTER);


    private Object Course;

    public void initialize() throws Exception {

        colID.setCellValueFactory(new PropertyValueFactory<>("registerID"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("stID"));
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFees.setCellValueFactory(new PropertyValueFactory<>("fees"));

        lastId();
        List<String> ids = courseBO.getAllCourseCodes();
        cmbCourseID.setItems(FXCollections.observableArrayList(ids));

        List<String> StIds = studentBO.getAllStudentsCodes();
        cmbStudentId.setItems(FXCollections.observableArrayList(StIds));

        cmbStudentId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setStudentData( newValue);
                    } catch (Exception throwables) {
                        throwables.printStackTrace();
                    }
                });

       cmbCourseID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData( newValue);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
       });

    }

    private void setStudentData(String Code) throws Exception {

        StudentDTO i= studentBO.findStudent(Code);
        if (i == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtName.setText(i.getFullName());
            txtAddress.setText(i.getAddress());
            txtNic.setText(i.getNIC());
            txtSchool.setText(i.getSchool());
            txtMobile.setText(i.getMobile());
            txtBirthday.setText(i.getBirthday());
        }
    }

    private void setCustomerData(String Id) throws Exception {
        CourseDTO c=courseBO.findCourse(Id);
        if (c == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtProgram.setText(c.getProgram());
            txtDuration.setText(c.getDuration());
            txtFees.setText(String.valueOf(c.getFees()));
        }
    }


    public void lastId() throws Exception {
        String Id = manageRegisterBO.getLastRegisterId();;
        String finalId = "R-001";

        if (Id != null) {

            String[] splitString = Id.split("-");
            int id = Integer.parseInt(splitString[1]);
            id++;

            if (id < 10) {
                finalId = "R-00" + id;
            } else if (id < 100) {
                finalId = "R-0" + id;
            } else {
                finalId = "R-" + id;
            }
            lblRegisterID.setText(finalId);
        } else {
            lblRegisterID.setText(finalId);
        }
    }

    private void calculateTotal() {
        Double total =0.00;
        for (RegisterTm detail : tblStudent.getItems()) {
            total = detail.getFees();
        }
        lblTotal.setText(String.valueOf(total));
    }



    public void RegisterOnAction(ActionEvent actionEvent) throws Exception {

        List<Course>  courseArrayList= new ArrayList<>();

        courseArrayList.add((entity.Course) Course);
        Course course=new Course(
                cmbCourseID.getSelectionModel().getSelectedItem(),
                txtProgram.getText(),
                txtDuration.getText(),
                Double.valueOf(txtFees.getText())
        );
        Student student = new Student(
                cmbStudentId.getSelectionModel().getSelectedItem(),
                txtName.getText(),
                txtAddress.getText(),
                txtNic.getText(),
                txtSchool.getText(),
                txtMobile.getText(),
                txtBirthday.getText()
        );

        RegisterDTO registration = new RegisterDTO(lblRegisterID.getText(),course.getFees(),student, courseArrayList);
        boolean saved =manageRegisterBO.RegisterStudent(registration);
        if (saved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Registration Successful", ButtonType.OK).show();
            cmbCourseID.getSelectionModel().clearSelection();
            txtProgram.clear();
            txtDuration.clear();
            txtFees.clear();
            cmbStudentId.getSelectionModel().clearSelection();
            txtName.clear();
            txtAddress.clear();
            txtNic.clear();
            txtSchool.clear();
            txtMobile.clear();
            txtBirthday.clear();

        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "Registration unsuccessful", ButtonType.OK).show();
        }

    }
    public void SelectOnAction(ActionEvent actionEvent) {
       String registerId= lblRegisterID.getText();
       String studentId=cmbStudentId.getSelectionModel().getSelectedItem();
       String courseId=cmbCourseID.getSelectionModel().getSelectedItem();
       String program=txtProgram.getText();
       String duration=txtDuration.getText();
       Double fees= Double.valueOf(txtFees.getText());

        tblStudent.getItems().add(new RegisterTm(registerId,studentId,courseId,program,duration,fees));
        calculateTotal();


    }
}

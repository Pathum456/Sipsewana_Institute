package dto;

import entity.Course;
import entity.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegisterDTO implements Serializable {
    String registerID;
    Double fees;
    private Student student;
    private List<Course> courses;

    public RegisterDTO() {
    }

    public RegisterDTO(String registerID, Double fees, Student student, List<Course> courses) {
        this.registerID = registerID;
        this.fees = fees;
        this.student = student;
        this.courses = courses;
    }

    public String getRegisterID() {
        return registerID;
    }

    public void setRegisterID(String registerID) {
        this.registerID = registerID;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Register implements SuperEntity{
    @Id
    String registerID;
    Double fees;
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course> courses;

    public Register() {
    }

    public Register(String registerID, Double fees, Student student, List<Course> courses) {
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

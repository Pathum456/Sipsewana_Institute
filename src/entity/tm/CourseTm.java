package entity.tm;

import javafx.scene.control.Button;

public class CourseTm {
    String courseID;
    String program;
    String duration;
    Double fees;
    private Button btn;

    public CourseTm() {
    }

    public CourseTm(String courseID, String program, String duration, Double fees, Button btn) {
        this.courseID = courseID;
        this.program = program;
        this.duration = duration;
        this.fees = fees;
        this.btn = btn;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}

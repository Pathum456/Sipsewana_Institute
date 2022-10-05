package dto;

import java.io.Serializable;

public class CourseDTO implements Serializable {
    String courseID;
    String program;
    String duration;
    Double fees;

    public CourseDTO() {
    }

    public CourseDTO(String courseID, String program, String duration, Double fees) {
        this.courseID = courseID;
        this.program = program;
        this.duration = duration;
        this.fees = fees;
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
}

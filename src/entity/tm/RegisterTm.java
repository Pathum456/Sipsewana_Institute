package entity.tm;

public class RegisterTm {
    String registerID;
    String stID;
    String courseID;
    String program;
    String duration;
   Double fees;

    public RegisterTm() {
    }

    public RegisterTm(String registerID, String stID, String courseID, String program, String duration, Double fees) {
        this.registerID = registerID;
        this.stID = stID;
        this.courseID = courseID;
        this.program = program;
        this.duration = duration;
        this.fees = fees;
    }

    public String getRegisterID() {
        return registerID;
    }

    public void setRegisterID(String registerID) {
        this.registerID = registerID;
    }

    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
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

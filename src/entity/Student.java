package entity;

import dto.StudentDTO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Student extends StudentDTO implements SuperEntity{
    @Id
    String stID;
    String fullName;
    String address;
    String NIC;
    String school;
    String mobile;
    String birthday;

    @OneToMany(mappedBy = "student")
    private List<Register> registerList;

    public Student() {
    }

    public Student(String stID, String fullName, String address, String NIC, String school, String mobile, String birthday) {
        this.stID = stID;
        this.fullName = fullName;
        this.address = address;
        this.NIC = NIC;
        this.school = school;
        this.mobile = mobile;
        this.birthday = birthday;
    }


    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


}

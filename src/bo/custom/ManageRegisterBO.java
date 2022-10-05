package bo.custom;

import bo.SuperBO;
import dto.CourseDTO;
import dto.RegisterDTO;
import dto.StudentDTO;
import entity.Course;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageRegisterBO extends SuperBO {
    boolean RegisterStudent(RegisterDTO dto) throws Exception;

    String generateNewId()throws SQLException, ClassNotFoundException;

    ArrayList<StudentDTO> getAllStudents() throws Exception;

    ArrayList<CourseDTO> getAllCourses() throws Exception;

    CourseDTO searchCourse(String code) throws Exception;

    boolean ifCourseExist(String code) throws SQLException, ClassNotFoundException;

    boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException;

    StudentDTO searchStudent(String s) throws Exception;

    public Course getCourse(String id) throws SQLException, ClassNotFoundException ;

    public Student getStudent(String id) throws SQLException, ClassNotFoundException ;

    String getLastRegisterId() throws Exception;
}

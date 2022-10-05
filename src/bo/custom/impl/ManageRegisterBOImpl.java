package bo.custom.impl;

import bo.custom.ManageRegisterBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.impl.CourseDAOImpl;
import dao.custom.impl.RegisterDAOImpl;
import dao.custom.impl.StudentDAOImpl;
import dto.CourseDTO;
import dto.RegisterDTO;
import dto.StudentDTO;
import entity.Course;
import entity.Register;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageRegisterBOImpl implements ManageRegisterBO {

    StudentDAOImpl studentDAO= DAOFactory.getInstance().getDAO(DAOType.STUDENT);
    CourseDAOImpl courseDAO= DAOFactory.getInstance().getDAO(DAOType.COURSE);
    RegisterDAOImpl registerDAO = DAOFactory.getInstance().getDAO(DAOType.REGISTER);
    @Override
    public boolean RegisterStudent(RegisterDTO dto) throws Exception {
      return registerDAO.add(new Register(
                dto.getRegisterID(),
              dto.getFees(),
              dto.getStudent(),
              dto.getCourses()

        ));

    }

    @Override
    public String generateNewId() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws Exception {
        ArrayList<StudentDTO> allStudents = new ArrayList<>();
        ArrayList<Student> all = (ArrayList<Student>) studentDAO.findAll();
        for (Student c : all) {
            allStudents.add(new StudentDTO(c.getStID(), c.getFullName(), c.getAddress(),c.getNIC(),
                    c.getSchool(),c.getMobile(),c.getBirthday()));
        }
        return allStudents;
    }

    @Override
    public ArrayList<CourseDTO> getAllCourses() throws Exception {
        ArrayList<CourseDTO> allCourses = new ArrayList<>();
        ArrayList<Course> all = (ArrayList<Course>) courseDAO.findAll();
        for (Course c : all) {
            allCourses.add(new CourseDTO(c.getCourseID(), c.getProgram(),c.getDuration() ,c.getFees()));
        }
        return allCourses;
    }

    @Override
    public CourseDTO searchCourse(String code) throws Exception {
        Course course = courseDAO.find(code);
        return new CourseDTO(course.getCourseID(), course.getProgram(),course.getDuration() ,course.getFees());
    }

    @Override
    public boolean ifCourseExist(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public StudentDTO searchStudent(String s) throws Exception {
        Student c = studentDAO.find(s);
        return new StudentDTO(c.getStID(), c.getFullName(), c.getAddress(),c.getNIC(),
                c.getSchool(),c.getMobile(),c.getBirthday());
    }

    @Override
    public Course getCourse(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Student getStudent(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public String getLastRegisterId() throws Exception {
            String lastId = registerDAO.getLastOrderId();
            return lastId;

    }
}

package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.Course;
import entity.Student;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {


    StudentDAOImpl studentDAO= DAOFactory.getInstance().getDAO(DAOType.STUDENT);

    @Override
    public boolean add(StudentDTO studentDTO) throws Exception {
        return studentDAO.add(new Student(
                studentDTO.getStID(),
                studentDTO.getFullName(),
                studentDTO.getAddress(),
                studentDTO.getNIC(),
                studentDTO.getSchool(),
                studentDTO.getMobile(),
                studentDTO.getBirthday()
        ));
    }

    @Override
    public List<StudentDTO> findAll() throws Exception {
        List<Student> all = studentDAO.findAll();
        ArrayList<StudentDTO> dtoList = new ArrayList<>();

        StudentDTO studentDTO = null;

        for (StudentDTO student : all) {
            dtoList.add(new StudentDTO(
                    student.getStID(),
                    student.getFullName(),
                    student.getAddress(),
                    student.getNIC(),
                    student.getSchool(),
                    student.getMobile(),
                    student.getBirthday()

            ));
        }
        return dtoList;

    }

    @Override
    public boolean delete(String id) throws Exception {
        return studentDAO.delete(id);
    }


    @Override
    public boolean update(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(new Student(
                studentDTO.getStID(),
                studentDTO.getFullName(),
                studentDTO.getAddress(),
                studentDTO.getNIC(),
                studentDTO.getSchool(),
                studentDTO.getMobile(),
                studentDTO.getBirthday()
        ));
    }

    @Override
    public List<String> getAllStudentsCodes() throws Exception {

        ArrayList<String> idList = new ArrayList<>();
        try {
            List<Student> all = studentDAO.findAll();
            for (Student dto: all) {
                idList.add(dto.getStID());
            }
            return idList;
        } catch (Exception e) {
            e.printStackTrace();
            return idList;
        }
    }

    @Override
    public StudentDTO findStudent(String stId) throws Exception {
            Student student = studentDAO.find(stId);
            return new StudentDTO(student.getStID(),
                    student.getFullName(), student.getAddress(),student.getNIC(),
                    student.getSchool(),student.getMobile(),student.getBirthday());
    }
}

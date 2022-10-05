package bo.custom.impl;

import bo.custom.CourseBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.impl.CourseDAOImpl;
import dao.custom.impl.StudentDAOImpl;
import dto.CourseDTO;
import dto.StudentDTO;
import entity.Course;
import entity.Student;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAOImpl courseDAO= DAOFactory.getInstance().getDAO(DAOType.COURSE);

    @Override
    public boolean add(CourseDTO courseDTO) throws Exception {
        return courseDAO.add(new Course(
                courseDTO.getCourseID(),
                courseDTO.getProgram(),
                courseDTO.getDuration(),
                courseDTO.getFees()

        ));
    }

    @Override
    public List<CourseDTO> findAll() throws Exception {
        List<Course> all = courseDAO.findAll();
        ArrayList<CourseDTO> dtoList = new ArrayList<>();

        //StudentDTO studentDTO = null;

        for (Course courseDTO : all) {
            dtoList.add(new CourseDTO(
                   courseDTO.getCourseID(),
                    courseDTO.getProgram(),
                    courseDTO.getDuration(),
                    courseDTO.getFees()

            ));
        }
        return dtoList;

    }

    @Override
    public boolean delete(String id) throws Exception {
        return courseDAO.delete(id);
    }


    @Override
    public boolean update(CourseDTO courseDTO) throws Exception {
        return courseDAO.update(new Course(
              courseDTO.getCourseID(),
                courseDTO.getProgram(),
                courseDTO.getDuration(),
                courseDTO.getFees()
        ));
    }
    @Override
    public List<String> getAllCourseCodes() throws Exception {

        ArrayList<String> idList = new ArrayList<>();
        try {
            List<Course> all = courseDAO.findAll();
            for (Course dto: all) {
                idList.add(dto.getCourseID());
            }
            return idList;
        } catch (Exception e) {
            e.printStackTrace();
            return idList;
        }
    }

    @Override
    public CourseDTO findCourse(String Id) throws Exception {
       Course course = courseDAO.find(Id);
        return new CourseDTO(course.getCourseID(),
               course.getProgram(),course.getDuration(),course.getFees());
    }
}

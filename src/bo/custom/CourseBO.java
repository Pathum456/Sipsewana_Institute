package bo.custom;

import bo.SuperBO;
import dto.CourseDTO;
import dto.StudentDTO;

import java.util.List;

public interface CourseBO extends SuperBO {
    public boolean add(CourseDTO courseDTO) throws Exception;

    public List<CourseDTO> findAll() throws Exception;

    public boolean delete(String id) throws Exception;

    public boolean update(CourseDTO courseDTO) throws Exception;

    List<String> getAllCourseCodes() throws Exception;

    CourseDTO findCourse(String Id) throws Exception;
}

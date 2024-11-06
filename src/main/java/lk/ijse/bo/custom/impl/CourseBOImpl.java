package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CourseBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CourseDAO;
import lk.ijse.dto.CourseDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.COURSEDAO);

    @Override
    public List<CourseDTO> getAllCourse() throws SQLException, ClassNotFoundException {
        ArrayList<Course> courses = (ArrayList<Course>) courseDAO.getAll();
        ArrayList<CourseDTO> courseDTO = new ArrayList<>();

        for (Course course: courses){
            courseDTO.add(new CourseDTO(course.getCourseId(), course.getCourseName(), course.getCourseDuration(), course.getCourseFee()));
        }
        return courseDTO;
    }

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws SQLException, ClassNotFoundException {
        return courseDAO.save(new Course(courseDTO.getCourseId(), courseDTO.getCourseName(), courseDTO.getCourseDuration(), courseDTO.getCourseFee()));
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws SQLException, ClassNotFoundException {
        return courseDAO.update(new Course(courseDTO.getCourseId(), courseDTO.getCourseName(), courseDTO.getCourseDuration(), courseDTO.getCourseFee()));
    }

    @Override
    public boolean deleteCourse(String id) throws SQLException, ClassNotFoundException {
        return courseDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public StudentDTO searchCourse(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

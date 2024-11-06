package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CourseDTO;
import lk.ijse.dto.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface CourseBO extends SuperBO {
    List<CourseDTO> getAllCourse() throws SQLException, ClassNotFoundException;
    boolean saveCourse(CourseDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateCourse(CourseDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteCourse(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    StudentDTO searchCourse(String id) throws SQLException, ClassNotFoundException;
}

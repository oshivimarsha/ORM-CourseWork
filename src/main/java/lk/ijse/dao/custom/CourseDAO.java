package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Course;
import lk.ijse.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO extends CrudDAO<Course> {
    List<Course> getAll() throws SQLException, ClassNotFoundException;

    Course searchByName(String name);
}

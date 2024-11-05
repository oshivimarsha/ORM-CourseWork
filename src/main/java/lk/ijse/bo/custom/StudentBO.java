package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException;
    boolean saveStudent(StudentDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;

}


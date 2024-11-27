package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ProgramDTO;
import lk.ijse.dto.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface ProgramBO extends SuperBO {
    List<ProgramDTO> getAllProgram() throws SQLException, ClassNotFoundException;
    boolean saveProgram(ProgramDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateProgram(ProgramDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteProgram(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    StudentDTO searchCourse(String id) throws SQLException, ClassNotFoundException;
    ProgramDTO searchProgramByName(String name);
    public List<String> getProgramNamesByStudentId(String studentId);
}

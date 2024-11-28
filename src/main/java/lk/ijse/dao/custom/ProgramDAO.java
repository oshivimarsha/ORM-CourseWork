package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Custom;
import lk.ijse.entity.Program;

import java.sql.SQLException;
import java.util.List;

public interface ProgramDAO extends CrudDAO<Program> {
    List<Program> getAll() throws SQLException, ClassNotFoundException;

    Program searchByName(String name);
    List<String> getProgramNamesByStudentId(String studentId);

    int getProgramCount() throws SQLException, ClassNotFoundException;

    List<Custom> getMostProgram();
}

package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ProgramBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ProgramDAO;
import lk.ijse.dto.ProgramDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Program;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAMDAO);

    @Override
    public List<ProgramDTO> getAllProgram() throws SQLException, ClassNotFoundException {
        ArrayList<Program> programs = (ArrayList<Program>) programDAO.getAll();
        ArrayList<ProgramDTO> programDTO = new ArrayList<>();

        for (Program program: programs){
            programDTO.add(new ProgramDTO(program.getProgramId(), program.getProgramName(), program.getProgramDuration(), program.getProgramFee()));
        }
        return programDTO;
    }

    @Override
    public boolean saveProgram(ProgramDTO programDTO) throws SQLException, ClassNotFoundException {
        return programDAO.save(new Program(programDTO.getProgramId(), programDTO.getProgramName(), programDTO.getProgramDuration(), programDTO.getProgramFee()));
    }

    @Override
    public boolean updateProgram(ProgramDTO programDTO) throws SQLException, ClassNotFoundException {
        return programDAO.update(new Program(programDTO.getProgramId(), programDTO.getProgramName(), programDTO.getProgramDuration(), programDTO.getProgramFee()));
    }

    @Override
    public boolean deleteProgram(String id) throws SQLException, ClassNotFoundException {
        return programDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return programDAO.generateNewID();
    }

    @Override
    public StudentDTO searchCourse(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ProgramDTO searchProgramByName(String name) {
        Program program = programDAO.searchByName(name);
        return new ProgramDTO(program.getProgramId(),program.getProgramName(),program.getProgramDuration(),program.getProgramFee());
    }

    @Override
    public List<String> getProgramNamesByStudentId(String studentId) {
        return programDAO.getProgramNamesByStudentId(studentId);
    }
}

package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;


public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENTDAO);//new CustomerDAOImpl();


    @Override
    public ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        return studentDAO.save(new Student(studentDTO.getStudentId(), studentDTO.getStudentFullName(), studentDTO.getStudentEmail(), studentDTO.getStudentContact(),studentDTO.getStudentNIC(), studentDTO.getStudentAddress(), studentDTO.getStudentDOB().toString(), studentDTO.getStudentAge(), studentDTO.getStudentGender(), studentDTO.getStudentPath()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return studentDAO.generateNewID();
    }
}

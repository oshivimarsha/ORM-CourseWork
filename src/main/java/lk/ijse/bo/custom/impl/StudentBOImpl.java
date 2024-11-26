package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;


public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENTDAO);


    @Override
    public ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException {
        ArrayList<Student> students = (ArrayList<Student>) studentDAO.getAll();
        ArrayList<StudentDTO> studentDTO = new ArrayList<>();

        for (Student student: students){
            studentDTO.add(new StudentDTO(student.getStudentId(), student.getStudentFullName(), student.getStudentEmail(), student.getStudentContact(),student.getStudentNIC(), student.getStudentAddress(), student.getStudentDOB(), student.getStudentAge(), student.getStudentGender(), student.getPath()));
        }
        return studentDTO;
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        return studentDAO.save(new Student(studentDTO.getStudentId(), studentDTO.getStudentFullName(), studentDTO.getStudentEmail(), studentDTO.getStudentContact(),studentDTO.getStudentNIC(), studentDTO.getStudentAddress(), studentDTO.getStudentDOB(), studentDTO.getStudentAge(), studentDTO.getStudentGender(), studentDTO.getStudentPath()));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        return studentDAO.update(new Student(studentDTO.getStudentId(), studentDTO.getStudentFullName(), studentDTO.getStudentEmail(), studentDTO.getStudentContact(),studentDTO.getStudentNIC(), studentDTO.getStudentAddress(), studentDTO.getStudentDOB(), studentDTO.getStudentAge(), studentDTO.getStudentGender(), studentDTO.getStudentPath()));
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return studentDAO.generateNewID();
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException {
        Student student = studentDAO.searchId(id);
        return new StudentDTO(student.getStudentId(), student.getStudentFullName(), student.getStudentEmail(), student.getStudentContact(), student.getStudentNIC(), student.getStudentAddress(), student.getStudentDOB(), student.getStudentAge(), student.getStudentGender(), student.getPath());
    }
}

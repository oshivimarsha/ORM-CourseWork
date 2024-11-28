package lk.ijse.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.bo.custom.RegisterBO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ProgramDAO;
import lk.ijse.dao.custom.RegisterDAO;
import lk.ijse.dto.ProgramDTO;
import lk.ijse.dto.RegisterDTO;
import lk.ijse.entity.Program;
import lk.ijse.entity.Register;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RegisterBOImpl implements RegisterBO {
    RegisterDAO registerDAO = (RegisterDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.REGISTERDAO);
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAMDAO);
    @Override
    public void placeRegister(List<RegisterDTO> registerDTOList) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        // boolean isUpdated =false;
        List<Register> registerList = new ArrayList<>();
        boolean isSaved = false;

        try{
            for (RegisterDTO registerDTO : registerDTOList) {
                Student student = new Student();
                student.setStudentId(registerDTO.getStuId());

                Program program = new Program();
                program.setProgramId(registerDTO.getCouId());

                Register register = new Register(/*registrationDTO.getRid(),*/student,program,registerDTO.getDate(),registerDTO.getPayment(),registerDTO.getAmountPay());
                registerList.add(register);

                isSaved = registerDAO.saveRegister(registerList,session);

            }

            if (isSaved) {
                transaction.commit();
                new Alert(Alert.AlertType.CONFIRMATION,"register completed").show();
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            transaction.rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public int getCurrentRegisterId() {
        return registerDAO.getCurrentId();
    }

    @Override
    public RegisterDTO searchProgramByName(String name) {
        return null;
    }
}

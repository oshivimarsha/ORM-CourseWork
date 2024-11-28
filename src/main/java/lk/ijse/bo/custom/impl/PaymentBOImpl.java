package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dao.custom.ProgramDAO;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dto.ProgramDTO;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Program;

import java.sql.SQLException;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENTDAO);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAMDAO);


    public Object[] getProgramPaymentDetails(String studentId, String programId){
        return queryDAO.getProgramPaymentDetails(studentId,programId);
    }

    public boolean savePayment(Payment payment) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(payment);
    }

    @Override
    public ProgramDTO searchProgramByName(String name) {
        Program program = programDAO.searchByName(name);
        return new ProgramDTO(program.getProgramId(),program.getProgramName(),program.getProgramDuration(),program.getProgramFee());
    }
}

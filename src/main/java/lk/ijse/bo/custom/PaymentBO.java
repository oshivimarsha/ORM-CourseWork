package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ProgramDTO;
import lk.ijse.entity.Payment;

import java.sql.SQLException;

public interface PaymentBO extends SuperBO {
    public Object[] getProgramPaymentDetails(String studentId, String programId);

    public boolean savePayment(Payment payment) throws SQLException, ClassNotFoundException;
    ProgramDTO searchProgramByName(String name);
}

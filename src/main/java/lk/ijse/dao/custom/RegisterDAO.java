package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Register;
import org.hibernate.Session;

import java.util.List;

public interface RegisterDAO extends CrudDAO<Register> {
    boolean saveRegister(List<Register> registerList, Session session);
    int getCurrentId();
}

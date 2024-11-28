package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    List<UserDTO> getAllUser() throws SQLException, ClassNotFoundException;
    boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    StudentDTO searchUser(String id) throws SQLException, ClassNotFoundException;

  //  User searchUserbyName(String userNameText);
}

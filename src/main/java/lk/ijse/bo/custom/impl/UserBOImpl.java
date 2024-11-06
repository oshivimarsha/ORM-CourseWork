package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.CourseDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.Course;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USERDAO);

    @Override
    public List<UserDTO> getAllUser() throws SQLException, ClassNotFoundException {
        ArrayList<User> users = (ArrayList<User>) userDAO.getAll();
        ArrayList<UserDTO> userDTO = new ArrayList<>();

        for (User user: users){
            userDTO.add(new UserDTO(user.getUserId(), user.getUserName(), user.getUserEmail(), user.getUserPosition(), user.getUserPassword()));
        }
        return userDTO;
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getUserEmail(), userDTO.getUserPosition(), userDTO.getUserPassword()));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getUserEmail(), userDTO.getUserPosition(), userDTO.getUserPassword()));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public StudentDTO searchUser(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

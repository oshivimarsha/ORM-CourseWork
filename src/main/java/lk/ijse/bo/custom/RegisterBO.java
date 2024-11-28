package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.RegisterDTO;

import java.util.List;

public interface RegisterBO extends SuperBO {
    void placeRegister(List<RegisterDTO> registerDTOList);
    int getCurrentRegisterId();
    RegisterDTO searchProgramByName(String name);
}

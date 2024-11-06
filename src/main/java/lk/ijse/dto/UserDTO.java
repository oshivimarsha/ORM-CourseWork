package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class UserDTO {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPosition;
    private String userPassword;
}

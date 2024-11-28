package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

@Entity
public class User {
    @Id
    private String userId;
    private String userName;
    private String userEmail;
    private String userPosition;
    private String userPassword;

    public String getPosition() {
        return userPosition;
    }
}

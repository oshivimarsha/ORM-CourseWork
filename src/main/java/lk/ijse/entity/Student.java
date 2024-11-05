package lk.ijse.entity;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class Student {
    @Id
    private String studentId;
    private String studentFullName;
    private String studentEmail;
    private String studentContact;
    private String studentNIC;
    private String studentAddress;
    private String studentDOB;
    private String studentAgr;
    private String studentGender;
    private String path;
}

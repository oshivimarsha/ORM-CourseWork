package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class StudentDTO {
    private String studentId;
    private String studentFullName;
    private String studentEmail;
    private String studentContact;
    private String studentNIC;
    private String studentAddress;
    private Date studentDOB;
    private String studentAge;
    private String studentGender;
    private String studentPath;
}

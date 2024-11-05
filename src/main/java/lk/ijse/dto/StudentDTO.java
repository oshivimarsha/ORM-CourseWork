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

    /*public StudentDTO(String studentId, String studentFullName, String studentEmail, String studentContact, String studentNIC, String studentAddress, String studentDOB, String studentAge) {
        this.studentId = studentId;
        this.studentFullName = studentFullName;
        this.studentEmail = studentEmail;
        this.studentContact = studentContact;
        this.studentNIC = studentNIC;
        this.studentAddress = studentAddress;
        this.studentDOB = studentDOB;
        this.studentAge = studentAge;
    }*/
}

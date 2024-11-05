package lk.ijse.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class StudentTm {
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

package lk.ijse.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

@Entity
public class Student {
    @Id
    private String studentId;
    private String studentFullName;
    private String studentEmail;
    private String studentContact;
    private String studentNIC;
    private String studentAddress;
    private Date studentDOB;
    private String studentAge;
    private String studentGender;
    private String path;

  //  @OneToMany(mappedBy = "student")
 //   List<Rejister> registrationList = new ArrayList<>();
}

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
public class Course {
    @Id
    private String courseId;
    private String courseName;
    private String courseDuration;
    private String courseFee;
}

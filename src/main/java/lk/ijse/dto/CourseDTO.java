package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class CourseDTO {
    private String courseId;
    private String courseName;
    private String courseDuration;
    private String courseFee;
}

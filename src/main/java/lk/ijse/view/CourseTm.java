package lk.ijse.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class CourseTm {
    private String courseId;
    private String courseName;
    private String courseDuration;
    private String courseFee;
}

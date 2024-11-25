package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

@Entity
public class Rejister {
    @Id
    private String rejisterId;
    private Date date;

    @ManyToOne
    private Student student;

    //private String pId;
    @ManyToOne
    private Course course;
  //  private double upfrontpayment;

   // @ManyToOne
   // private Payment payment;
   // private Date date;

}

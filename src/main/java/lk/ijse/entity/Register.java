package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

@Entity
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String registerId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    @ManyToOne
    private Program course;

    private LocalDate date;
    private double payment;
    private double amountPay;

    public Register(Student student, Program program, LocalDate date, double payment, double amountPay) {
        this.student = student;
        this.course = program;
        this.date = date;
        this.payment = payment;
        this.amountPay = amountPay;
    }
}

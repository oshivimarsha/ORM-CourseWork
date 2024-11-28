package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Custom {
    private String date;
    private int count;

    private String programId;
    /*private int orderCount;
    private int sumQty;*/

    public Custom(String date, int count){
        this.date = date;
        this.count = count;
    }
    public Custom(String programId /*, int orderCount, int sumQty*/){
        this.programId = programId;
        /*this.orderCount = orderCount;
        this.sumQty = sumQty;*/
    }
}

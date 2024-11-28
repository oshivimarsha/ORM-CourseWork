package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomDTO {
    private String date;
    private int count;

    private String programId;
    /*private int orderCount;
    private int sumQty;*/

    public CustomDTO(String date, int count){
        this.date = date;
        this.count = count;
    }
    public CustomDTO(String programId /*, int orderCount, int sumQty*/){
        this.programId = programId;
        /*this.orderCount = orderCount;
        this.sumQty = sumQty;*/
    }
}

import lombok.*;

@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
public class HouseDTO {
    @MyIdentificator
    private int id;
    @MyColumn
    private double size;
    @MyColumn
    private String color;
    @MyColumn
    private int roomCount;
}

import lombok.*;

@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
public class DoorDTO {
    @MyIdentificator
    private int id;
    @MyColumn
    private double size;
    @MyColumn
    private String type;

}

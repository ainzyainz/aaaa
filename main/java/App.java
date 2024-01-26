
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {


        try (Connection connection1 = DriverManager.getConnection(JDBCResource.getURL2(), JDBCResource.getUser(), JDBCResource.getPassword())) {
            DoorDTO doorDAO = DoorDTO.builder().size(9.9).type("doorDAO").build();

            DAOImplDoor1 doors = new DAOImplDoor1(connection1);
               doors.save(doorDAO);
               doors.delete(2);
            DoorDTO mydoor = doors.findById(6);
            System.out.println(mydoor);
            doors.update(doorDAO, 7);

/*
            HouseDTO houseDAO = HouseDTO
                    .builder()
                    .size(9.9)
                    .color("yellow")
                    .roomCount(4)
                    .build();

            DAOImplHouse1 houses = new DAOImplHouse1(connection1);

            houses.save(houseDAO);
*/
            //houses.save(houseDAO,6);
          //  houses.delete(11);
            //houses.update(houseDAO,5);

          /*  HouseDTO tempHouse = houses.findById(6);
            System.out.println(tempHouse);*/

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

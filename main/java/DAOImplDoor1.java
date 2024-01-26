import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOImplDoor1 extends DAOImpl<DoorDTO> implements doorDAO{
    public DAOImplDoor1(Connection connection) {
        super(connection);
    }

    public void update(DoorDTO entity, int id) throws SQLException {
        super.update("door.door",DoorDTO.class,id,entity);
        }

    public void delete(int id) throws SQLException{
        super.delete("door.door",DoorDTO.class,id);
    }
    public void save(DoorDTO entity) throws SQLException {
        super.save("door.door",DoorDTO.class,entity);
    }
    public DoorDTO findById(int id) throws SQLException {
        try (ResultSet rs = findById("door.door", id, DoorDTO.class)) {
            return new DoorDTO(rs.getInt("id"),rs.getDouble("size"), rs.getString("type"));
        }
    }
}


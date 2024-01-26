import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOImplHouse1 extends DAOImpl<HouseDTO> implements houseDAO{

    public DAOImplHouse1(Connection connection) {
        super(connection);
    }


    @Override
    public void save(HouseDTO entity) throws SQLException {
        super.save("house.house",HouseDTO.class,entity);
    }

    @Override
    public void update(HouseDTO entity, int id) throws SQLException {
        super.update("house.house",HouseDTO.class,id,entity);
        }

    @Override
    public void delete(int id) throws SQLException {
        super.delete("house.house",HouseDTO.class,id );
    }

    @Override
    public HouseDTO findById(int id) throws SQLException {
        try(ResultSet rs = findById("house.house",id,HouseDTO.class)){
            return new HouseDTO(rs.getInt("id"),rs.getDouble("size"),rs.getString("color"),rs.getInt("roomCount"));
        }
    }
}

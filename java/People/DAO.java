package People;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAO<T> {
    void save(Connection connection, T person) throws SQLException;
    void update(T obj, Connection connection, int x) throws SQLException;
    void delete(Connection connection, int x) throws SQLException;
    T findById(int id, Connection connection);
}

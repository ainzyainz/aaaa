import java.sql.Connection;
import java.sql.SQLException;

public interface DAO<T> {
    void save(T person) throws SQLException;
    void update(T obj, int x) throws SQLException;
    void delete(int x) throws SQLException;
    T findById(int id) throws SQLException;
}

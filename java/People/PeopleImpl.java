package People;

import java.sql.*;

public class PeopleImpl implements PeopleDAO {

    public static final String INSERT_INTO_PEOPLE = "INSERT INTO people.person (age, salary, passport, address, dateOfBirthday, dateTimeCreate, timeToLunch, ` letter`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_SQL = "\n" +
            "UPDATE person\n" +
            "SET age = ?, salary = ?, passport = ?, address = ?, dateOfBirthday = ?, dateTimeCreate = ?, timeToLunch = ?, ` letter` = ?\n" +
            "where person.id = ?;";
    public static final String DELETE_QUERY = "DELETE from person where id = ?;";
    public static final String SELECT_QUERY = "SELECT * FROM person where person.id = ?;";

    @Override
    public void save(Connection connection, PersonDTO person) {
        try(PreparedStatement ps = connection.prepareStatement(INSERT_INTO_PEOPLE)){
            ps.setInt(1,person.getAge());
            ps.setDouble(2, person.getSalary());
            ps.setString(3,person.getPassport());
            ps.setString(4,person.getAddress());
            ps.setDate(5, (Date) person.getDateOfBd());
            ps.setTimestamp(6,person.getDateTimeCreate());
            ps.setTime(7,person.getTime());
            ps.setString(8,person.getLetter());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(PersonDTO person, Connection connection, int x) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)){
            ps.setInt(1,person.getAge());
            ps.setDouble(2, person.getSalary());
            ps.setString(3,person.getPassport());
            ps.setString(4,person.getAddress());
            ps.setDate(5, (Date) person.getDateOfBd());
            ps.setTimestamp(6,person.getDateTimeCreate());
            ps.setTime(7,person.getTime());
            ps.setString(8,person.getLetter());
            ps.setInt(9,x);
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Connection connection, int x) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)){
            ps.setInt(1,x);
            ps.executeUpdate();
        }
    }

    @Override
    public PersonDTO findById(int id, Connection connection) {
        try(PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);) {
            ps.setInt(1,id);

            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                return new PersonDTO(rs.getInt("age"),
                        rs.getDouble("salary"),
                        rs.getString("passport"),
                        rs.getString("address"),
                        rs.getDate("dateOfBirthday"),
                        rs.getTimestamp("dateTimeCreate"),
                        rs.getTime("timeToLunch"),
                        rs.getString(" letter")
                );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.*;
import lombok.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBCResource.getURL(), JDBCResource.getUser(), JDBCResource.getPassword())) {

           // addingCustom(connection);
            List<PersonDTO> people =  extr(connection);
            
            for(PersonDTO temp : people) System.out.println(temp);

            DatabaseMetaData metaData = connection.getMetaData();
            List tables = getTables(metaData,"people");
            System.out.println(tables);
            List columns = getColumns(metaData,"people");
            System.out.println(columns);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static List getTables(DatabaseMetaData metaData, String catalog) throws SQLException {
        ResultSet rs = metaData.getTables(catalog,null,null,null);
        List tables = new ArrayList();
        while(rs.next()){
            tables.add(rs.getString("TABLE_NAME"));
        }
        return tables;
    }
    private static List getColumns(DatabaseMetaData metaData, String catalog) throws SQLException {
        List columns = new ArrayList();
        ResultSet rs = metaData.getColumns("people",null,null,null);
        while(rs.next()){
            columns.add(rs.getString("COLUMN_NAME"));
        }
        return columns;

    }

    private static void addingCustom(Connection connection) throws SQLException {
        Date myDate = new GregorianCalendar(2003, Calendar.MARCH, 11).getTime();
        long myTime = System.currentTimeMillis();
        try (PreparedStatement preparedStatement = connection.prepareStatement(customPerson.CUSTOM_PERSON_STATEMENT)) {
            connection.setAutoCommit(false);
            try {
                customPerson.insertPerson(preparedStatement, 11,
                        234.1,
                        "MP123222",
                        "Busan",
                        myDate,
                        myTime,
                        myTime,
                        "bla bla");

                customPerson.insertPerson(preparedStatement, 1,
                        234.1,
                        "MP123222",
                        "Tokyo",
                        new GregorianCalendar(2003, Calendar.OCTOBER, 11).getTime(),
                        myTime,
                        myTime,
                        "badsda");

                customPerson.insertPerson(preparedStatement, 22,
                        234.1,
                        "MP123222",
                        "Seoul",
                        myDate,
                        myTime,
                        myTime,
                        "bladda");

                customPerson.insertPerson(preparedStatement, 23,
                        234.1,
                        "MP123222",
                        "Busan",
                        myDate,
                        myTime,
                        myTime,
                        "blaa");

                customPerson.insertPerson(preparedStatement, 24,
                        234.1,
                        "MP123222",
                        "Busan",
                        myDate,
                        myTime,
                        myTime,
                        "blaasdaa");


                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }
        }
    }

    private static List extr(Connection connection) throws SQLException {
        List<PersonDTO> people = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM people.person\n" +
                "WHERE age > 21 ORDER BY dateTimeCreate");
        while (resultSet.next()){
            PersonDTO temp = PersonDTO
                    .builder()
                    .age(resultSet.getInt("age"))
                    .salary(resultSet.getDouble("salary"))
                    .passport(resultSet.getString("passport"))
                    .address(resultSet.getString("address"))
                    .dateOfBd(resultSet.getDate("dateOfBirthday"))
                    .dateTimeCreate(resultSet.getTimestamp("dateTimeCreate"))
                    .time(resultSet.getTime("timeToLunch"))
                    .letter(resultSet.getString(" letter"))
                    .build();
            people.add(temp);
        }
        return people;
    }
}
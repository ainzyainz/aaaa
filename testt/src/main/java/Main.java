import java.sql.*;
import java.util.*;
import java.util.Date;

public class Main {

    public static final String TAB_NAME_CONST = "TABLE_NAME";
    public static final String COL_NAME_CONST = "COLUMN_NAME";
    public static final String SQL_SELECT_QUERY = "SELECT * FROM people.person\n" +
            "WHERE age > 21 ORDER BY dateTimeCreate";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBCResource.getURL(), JDBCResource.getUser(), JDBCResource.getPassword())) {

           addingCustom(connection);
            List<PersonDTO> people = extracting(connection);

            people.stream().forEach(System.out::println);


            DatabaseMetaData metaData = connection.getMetaData();
            List tables = getTables(metaData,"people");
            System.out.println(tables);
            List columns = getColumns(metaData,"people");
            System.out.println(columns);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static List<String> getTables(DatabaseMetaData metaData, String catalog) throws SQLException {
        ResultSet rs = metaData.getTables(catalog,null,null,null);
        List<String> tables = new ArrayList();
        while(rs.next()){
            tables.add(rs.getString(TAB_NAME_CONST));
        }
        rs.close();
        return tables;
    }
    private static List<String> getColumns(DatabaseMetaData metaData, String catalog) throws SQLException {
        List<String> columns = new ArrayList();
        ResultSet rs = metaData.getColumns(catalog,null,null,null);
        while(rs.next()){
            columns.add(rs.getString(COL_NAME_CONST));
        }
        return columns;

    }

    private static void addingCustom(Connection connection) throws SQLException {
        Date myDate = new GregorianCalendar(2003, Calendar.MARCH, 11).getTime();
        long myTime = System.currentTimeMillis();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CustomPerson.CUSTOM_PERSON_STATEMENT)) {
            connection.setAutoCommit(false);
            try {
                CustomPerson.insertPerson(preparedStatement, 11,
                        234.1,
                        "MP123222",
                        "Busan",
                        myDate,
                        myTime,
                        myTime,
                        "bla bla");

                CustomPerson.insertPerson(preparedStatement, 1,
                        234.1,
                        "MP123222",
                        "Tokyo",
                        new GregorianCalendar(2003, Calendar.OCTOBER, 11).getTime(),
                        myTime,
                        myTime,
                        "badsda");

                CustomPerson.insertPerson(preparedStatement, 22,
                        234.1,
                        "MP123222",
                        "Seoul",
                        myDate,
                        myTime,
                        myTime,
                        "bladda");

                CustomPerson.insertPerson(preparedStatement, 23,
                        234.1,
                        "MP123222",
                        "Busan",
                        myDate,
                        myTime,
                        myTime,
                        "blaa");

                CustomPerson.insertPerson(preparedStatement, 24,
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

    private static List<PersonDTO> extracting(Connection connection) throws SQLException {
        List<PersonDTO> people = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_QUERY);
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

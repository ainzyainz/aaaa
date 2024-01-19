import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class customPerson {

        public static final String CUSTOM_PERSON_STATEMENT =
                "INSERT INTO people.person (age, salary, passport, address, dateOfBirthday, dateTimeCreate, timeToLunch, ` letter`) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        public static void insertPerson(PreparedStatement ps,
                                     int age,
                                     double salary,
                                     String passport,
                                     String address,
                                     Date dateOfBd,
                                     long dateTimeCreate, //maybe
                                     long time,
                                     String letter
        ) throws SQLException {
            ps.setInt(1,age);
            ps.setDouble(2,salary);
            ps.setString(3,passport);
            ps.setString(4,address);
            ps.setDate(5, new java.sql.Date(dateOfBd.getTime()));
            ps.setTimestamp(6, new java.sql.Timestamp(dateTimeCreate));
            ps.setTime(7,new java.sql.Time(time));
            ps.setString(8,letter);
            ps.executeUpdate();
        }
    }


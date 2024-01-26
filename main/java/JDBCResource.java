import java.util.ResourceBundle;

public class JDBCResource {
    public static final String DATABASE_RESOURS = "database";
    private static final String URL_KEY = "url";
    private static final String URL2_KEY = "url2";
    private static final String USER_KEY = "user";
    private static final String PASSWORD_KEY = "password";


    private static final ResourceBundle resource = ResourceBundle.getBundle(DATABASE_RESOURS);


    private static final String URL = getValue(URL_KEY);
    private static final String URL2 = getValue(URL2_KEY);
    private static final String USER = getValue(USER_KEY);
    private static final String PASSWORD = getValue(PASSWORD_KEY);


    private static String getValue(String key) {
        return resource.getString(key);
    }


    public static String getURL() {
        return URL;
    }
    public static String getURL2() {
        return URL2;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }

}
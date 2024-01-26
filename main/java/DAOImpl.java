import java.lang.reflect.Field;
import java.sql.*;

public abstract class DAOImpl<T> {
    private Connection connection;
    public DAOImpl(Connection connection) {
        this.connection = connection;
    }

    //UPDATE door.door set size = ?, type = ? WHERE id = ?

    protected void update(String table, Class<T> tClass, int id, T obj) throws SQLException {

        String command = creatingCommand2(table, tClass);

        PreparedStatement ps = connection.prepareStatement(command);

        Field[] objFields = tClass.getDeclaredFields();

        for(int i = 0; i < objFields.length; i++){
            if (objFields[i].isAnnotationPresent(MyColumn.class)){
                try {
                    objFields[i].setAccessible(true);
                    Object value = objFields[i].get(obj);
                    ps.setObject(i,value);
                    if (i==objFields.length-1){
                        ps.setObject(i+1,id);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ps.executeUpdate();
        ps.close();
    }

    private String creatingCommand2(String table, Class<T> tClass) {
        String SQLCommand = "UPDATE "+table+" set ";
        StringBuilder st = new StringBuilder(SQLCommand);
        Field[] fields = tClass.getDeclaredFields();

        for(int i = 0; i < fields.length; i++){
            if (fields[i].isAnnotationPresent(MyColumn.class)){
                st.append(fields[i].getName()+" = ?");
            }
            if (fields[i].isAnnotationPresent(MyColumn.class) &&  i!=fields.length-1){
                st.append(",");
            }
        }
        st.append(" WHERE ");


        String identity = null;

        for(Field temp : fields){
            if (temp.isAnnotationPresent(MyIdentificator.class)){
                identity = temp.getName();
            }
        }

            st.append(identity+" = ?");

        System.out.println(st.toString());
        return st.toString();
    }

    protected void delete(String table, Class<T> tClass,int id) throws SQLException {

        String identity = null;

        for(Field temp: tClass.getDeclaredFields()){
            if (temp.isAnnotationPresent(MyIdentificator.class)){
                identity = temp.getName();
            }
        }
        String SQLCommand = "DELETE FROM "+table+" WHERE "+identity +" = ?";
        System.out.println(SQLCommand);

        try(PreparedStatement ps = connection.prepareStatement(SQLCommand)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }
    }


    protected void save(String table, Class<T> tClass, T obj) throws SQLException {

        String SAVE = creatingCommand(tClass, "INSERT INTO " + table);

        Field[] objFields = obj.getClass().getDeclaredFields();


        PreparedStatement ps = connection.prepareStatement(SAVE);


        for(int i = 0; i < objFields.length; i++){
            if (objFields[i].isAnnotationPresent(MyColumn.class)){
                try {
                    objFields[i].setAccessible(true);
                    Object value = objFields[i].get(obj);
                    ps.setObject(i,value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ps.executeUpdate();
        ps.close();
    }

    private String creatingCommand(Class<T> tClass, String SQLcommand) {
        StringBuilder st = new StringBuilder(SQLcommand);
        Field[] fields = tClass.getDeclaredFields();
        st.append("(");

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(MyColumn.class)) {
                st.append(fields[i].getName());
                if (fields[i].isAnnotationPresent(MyColumn.class)&&i!=fields.length-1){
                    st.append(",");
                }
            }
        }

        st.append(") ");



        if (fields.length == 1) {
            st.append("VALUE ?");
            return st.toString();
        }

        st.append("VALUES(");

        for (int i = 0; i < fields.length - 1; i++) {
            if (fields[i].isAnnotationPresent(MyColumn.class)) {
                st.append("?,");
            }
        }

        st.append("?)");

        System.out.println(st.toString());

        return st.toString();
    }

    protected ResultSet findById(String table, int id, Class<T> tClass) throws SQLException {
        String identity = null;
        for(Field temp: tClass.getDeclaredFields()){
            if (temp.isAnnotationPresent(MyIdentificator.class)){
                identity = temp.getName();
            }
        }


        PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE "+identity+" = ?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs;

    }
}

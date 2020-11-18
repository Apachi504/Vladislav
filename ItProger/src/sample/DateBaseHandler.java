package sample;

import javax.naming.Name;
import javax.xml.stream.Location;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DateBaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException,SQLException{ // подключение к БД
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName; // подключаемся к MySQL Drivers

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    public void singUpUser(User user){ //Ввод данных в БД
        String insert = "INSERT INTO "+Const.USERS_TABEL + "(" + Const.USERS_FIRST_NAME + "," + Const.USERS_LAST_NAME + "," +
                Const.USERS_USER_NAME + "," + Const.USERS_PASSWORD + "," +Const.USERS_GENDER + ","+Const.USERS_LOCATION +")"+
                "VALUES(?,?,?,?,?,?)";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getGender());
            prSt.setString(6, user.getLocation());


            prSt.executeUpdate(); // метод который позволяет закинуть в БД
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user){ // Выгрузка из БД параметров
        ResultSet resSet = null;

        String select = "SELECT * FROM "+ Const.USERS_TABEL + " WHERE " + Const.USERS_USER_NAME + "=? AND " + Const.USERS_PASSWORD +"=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());
// SELECT - выбрать , * - все , FROM - из , имя таблицы ,WHERE - где , =? - имя пользователя будет равен чему либо, AND и , пароль будет равен чему либо =?//

            resSet = prSt.executeQuery(); // метод который позволяет выгрузить из БД что либо
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            return resSet;// Возврашаем значения из БД
    }

}

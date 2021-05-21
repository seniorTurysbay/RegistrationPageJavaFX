package sample;
import com.sun.org.apache.bcel.internal.Const;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbUser,dbUser,dbPass);
        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Constant.USER_TABLE + "(" +
                Constant.USERS_FIRSTNAME + "," + Constant.USERS_LASTNAME + "," + Constant.USERS_USERNAME + "," + Constant.USERS_PASSWORD +","+
                Constant.USERS_LOCATION+","+Constant.USERS_GENDER+")"+
                "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prSt  = getDbConnection().prepareStatement(insert);
            prSt.setString(1,user.getFirstName());
            prSt.setString(2,user.getLastName());
            prSt.setString(3,user.getUserName());
            prSt.setString(4,user.getPassword());
            prSt.setString(5,user.getLocation());
            prSt.setString(6,user.getGender());
            //executeUpdate - send smth to db
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Constant.USER_TABLE + " WHERE " +
                Constant.USERS_USERNAME + "=? AND " + Constant.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt  = getDbConnection().prepareStatement(select);
            prSt.setString(1,user.getUserName());
            prSt.setString(2,user.getPassword());
//          // executeQuery - get smth from db
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}

package sample;

import sample.controllers.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DatabaseHandler extends Config {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost
                                    + ":" + dbPort + "/" + dbName+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);

        return dbConnection;
    }

    public void signUpUser(User user){
        String insert = "INSERT INTO " + Constant.USER_TABLE + " (" + Constant.USERS_LOGIN + ","
                        + Constant.USERS_PASSWORD + "," + Constant.USERS_EMAIL+ ","
                        + Constant.USERS_CITY + "," + Constant.USERS_GENDER + ") VALUES (?,?,?,?,?)";

        try {
            PreparedStatement prST = getDbConnection().prepareStatement(insert);
            prST.setString(1, user.getLogin());
            prST.setString(2, user.getPassword());
            prST.setString(3, user.getEmail());
            prST.setString(4, user.getCity());
            prST.setString(5, user.getGender());
            prST.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Constant.USER_TABLE + " WHERE " + Constant.USERS_LOGIN + " =? AND " +
                        "" + Constant.USERS_PASSWORD + " =?";
        try {
            PreparedStatement prST = getDbConnection().prepareStatement(select);
            prST.setString(1, user.getLogin());
            prST.setString(2, user.getPassword());

            resSet = prST.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

}

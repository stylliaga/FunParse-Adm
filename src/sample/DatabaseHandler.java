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

    public void updateUser(User user){
        String string1 = null;
        String string2 = null;
        String string3 = null;
        String string4 = null;
        String string5 = null;
        String string6 = null;
        String string7 = null;

        if(user.getPassword() != ""){
            string1 = "" + Constant.USERS_PASSWORD + "=?, ";
        }
        if(user.getEmail() != ""){
            string2 = Constant.USERS_EMAIL + "=?, ";
        }
        if(user.getLanguage() != ""){
            string3 = Constant.USERS_LANGUAGE + "=?, ";
        }
        if(user.getOnly18() != ""){
            string4 = Constant.USERS_ONLY18 + "=?, ";
        }
        if(user.getSaveFolder() != ""){
            string5 = Constant.USERS_SAVE_FOLDER + "=?,";
        }
        if(user.getSaveOnComp() != ""){
            string6 = Constant.USERS_SAVE_ON_COMP + "=?, ";
        }
        if(user.getSortBy() != ""){
            string7 = Constant.USERS_SORT_BY + "=?";
        }
       String update =  "UPDATE " + Constant.USER_TABLE + " SET " + "" +
               "" + string1 + "" +
               "" + string2 + "" +
               "" + string3 + "" +
               "" + string4 + "" +
               "" + string5 + "" +
               "" + string6 + "" +
               "" + string7 + " WHERE " +
               "" + Constant.USERS_LOGIN + "=?";

        try {
            PreparedStatement prST = getDbConnection().prepareStatement(update);
            prST.setString(1, user.getPassword());
            prST.setString(2, user.getEmail());
            prST.setString(3, user.getLanguage());
            prST.setString(4, user.getOnly18());
            prST.setString(5, user.getSaveFolder());
            prST.setString(6, user.getSaveOnComp());
            prST.setString(7, user.getSortBy());
            prST.setString(8, user.getLogin());
            prST.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}

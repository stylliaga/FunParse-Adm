package sample;

import sample.controllers.User;

import javax.swing.*;
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

    public ResultSet updateUser(User user){
        ResultSet resSet = null;
        String string1 = "";
        String string2 = "";
        String string3 = "";
        String string4 = "";
        String string5 = "";
        String string6 = "";
        String string7 = "";
        String string8 = "";
        String string9 = "";

        try {

            if(user.getPassword() != ""){
                string1 = "" + Constant.USERS_PASSWORD + "='" + user.getPassword() +"', ";
                 //= ;
            }
            if(user.getEmail() != ""){
                string2 = Constant.USERS_EMAIL + "='" + user.getEmail() + "', ";
                //userGets2 = ;
            }
            if(user.getLanguage() != ""){
                string3 = Constant.USERS_LANGUAGE + "='" + user.getLanguage() + "', ";
                //userGets3 = ;
            }
            if(user.getOnly18() != ""){
                string4 = Constant.USERS_ONLY18 + "='" + user.getOnly18() + "', ";
                //userGets4 = ;
            }
            if(user.getSaveFolder() != ""){
                string5 = Constant.USERS_SAVE_FOLDER + "='" + user.getSaveFolder() + "', ";
                //userGets5 = ;
            }
            if(user.getSaveOnComp() != ""){
                string6 = Constant.USERS_SAVE_ON_COMP + "='" + user.getSaveOnComp() + "', ";
                //userGets6 = ;
            }
            if(user.getSortBy() != ""){
                string7 = Constant.USERS_SORT_BY + "='" + user.getSortBy() + "', ";
                //userGets7 = ;
            }
            if(user.getUrlSiteToParse() != ""){
                string8 = Constant.USERS_URL_PARSE + "='" + user.getUrlSiteToParse() + "',";
                //userGets8 = ;
            }
            if(user.getHistory() != ""){
                string9 = Constant.USERS_HYSTORY + "='" + user.getHistory() + "'";
                //userGets8 = ;
            }
            String update =  "UPDATE " + Constant.USER_TABLE + " SET " + "" +
                    "" + string1 + "" +
                    "" + string2 + "" +
                    "" + string3 + "" +
                    "" + string4 + "" +
                    "" + string5 + "" +
                    "" + string6 + "" +
                    "" + string7 + "" +
                    "" + string8 + "" +
                    "" + string9 + " WHERE " +
                    "" + Constant.USERS_LOGIN + "=?";
            System.out.println(update);

            PreparedStatement prST = getDbConnection().prepareStatement(update);
            prST.setString(1, user.getLogin());
            prST.executeUpdate();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }


}

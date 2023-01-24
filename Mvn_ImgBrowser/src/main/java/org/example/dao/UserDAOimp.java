package org.example.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOimp implements UserDAO{

    final String DB="jdbc:sqlite:src\\main\\resources\\org\\example\\database\\real.db";
    final String ADD_USER="INSERT INTO users (username, password,password_salt) VALUES (?,?,?)";
    final String ADD_USER_ROLE="INSERT INTO user_roles (username, role_name)VALUES (?,?)";
    final String UPDATE_USER_ROLE="UPDATE user_roles SET role_name =? WHERE username=?";
    final String ALL_USERNAME="SELECT username from users";
    final String ALL_ROLES="SELECT role_name from roles_permissions";
    final String DELETE_USER_OF_USERS="DELETE FROM users WHERE username = ?;";
    final String DELETE_USER_OF_USER_ROLES="DELETE FROM user_roles WHERE username = ?;";




    @Override
    public boolean addUser(String n, String p, String ps) {
        try (Connection conn = DriverManager.getConnection(DB);
             PreparedStatement preparedStatement = conn.prepareStatement(ADD_USER)){
            preparedStatement.setString(1,n);
            System.out.println("name: "+n);
            preparedStatement.setString(2,p);
            System.out.println("pwd: "+p);
            preparedStatement.setString(3,ps);
            System.out.println("salted_pwd: "+ps);
            int res= preparedStatement.executeUpdate();
            if (res==1){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean addUserRole(String n, String r) {
        try (Connection conn = DriverManager.getConnection(DB);
             PreparedStatement preparedStatement = conn.prepareStatement(ADD_USER_ROLE)){
            preparedStatement.setString(1,n);
            System.out.println("name: "+n);
            preparedStatement.setString(2,r);
            System.out.println("role: "+r);
            int res= preparedStatement.executeUpdate();
            if (res==1){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateUserRole(String r, String name) {
        try (Connection conn = DriverManager.getConnection(DB);
             PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_USER_ROLE)){
            preparedStatement.setString(1,r);
            System.out.println("role: "+r);
            preparedStatement.setString(2,name);
            System.out.println("name: "+name);
            int res= preparedStatement.executeUpdate();
            if (res==1){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteUserOfUsers( String uname) {
        try (Connection conn = DriverManager.getConnection(DB);
             PreparedStatement preparedStatement = conn.prepareStatement(DELETE_USER_OF_USERS)){
            preparedStatement.setString(1,uname);
            System.out.println("DELETE_USER_OF_USERS : "+uname);

            int res= preparedStatement.executeUpdate();
            if (res==1){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteUserOfUserRoles( String uname) {
        try (Connection conn = DriverManager.getConnection(DB);
             PreparedStatement preparedStatement = conn.prepareStatement(DELETE_USER_OF_USER_ROLES)){
            preparedStatement.setString(1,uname);
            System.out.println("DELETE_USER_OF_USER_ROLES : "+uname);

            int res= preparedStatement.executeUpdate();
            if (res==1){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ObservableList AllUsername() {
         ObservableList options = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection(DB);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(ALL_USERNAME)){

            while (rs.next()) {
                options.add(rs.getString("username"));
                //System.out.println("username: "+rs.getString("username"));
            }
            return options;
        }catch (Exception e){
            System.out.println("get all exception");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList AllRole() {
        ObservableList options = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection(DB);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(ALL_ROLES)){

            while (rs.next()) {
                options.add(rs.getString("role_name"));
                //System.out.println("username: "+rs.getString("username"));
            }
            return options;
        }catch (Exception e){
            System.out.println("get all exception");
            e.printStackTrace();
        }
        return null;
    }


}

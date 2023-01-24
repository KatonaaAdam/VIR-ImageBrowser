package org.example.dao;

import javafx.collections.ObservableList;

public interface UserDAO {
    public boolean addUser(String n ,String p,String ps); // user registration
    public boolean addUserRole(String n ,String r); //set user user role
    public boolean updateUserRole(String name ,String r); // user role modify
    public boolean deleteUserOfUsers(String uname); // delete user from users
    public boolean deleteUserOfUserRoles(String uname); //delete user from user_roles
    public ObservableList AllUsername(); //list all username
    public ObservableList AllRole(); //list all user role
}

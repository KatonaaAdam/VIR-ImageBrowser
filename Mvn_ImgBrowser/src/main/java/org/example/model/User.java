package org.example.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    StringProperty name=new SimpleStringProperty();
    StringProperty pwd=new SimpleStringProperty();
    StringProperty pwd_salt=new SimpleStringProperty();



    public User(){}
    public User(String n , String p, String ps, String r){
        this.name.set(n);
        this.pwd.set(p);
        this.pwd_salt.set(ps);

    }
    public User(String n){this.name.set(n);}
   // User(String role){this.role.set(role);}

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPwd() {
        return pwd.get();
    }

    public StringProperty pwdProperty() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd.set(pwd);
    }

    public String getPwd_salt() {
        return pwd_salt.get();
    }

    public StringProperty pwd_saltProperty() {
        return pwd_salt;
    }

    public void setPwd_salt(String pwd_salt) {
        this.pwd_salt.set(pwd_salt);
    }

}

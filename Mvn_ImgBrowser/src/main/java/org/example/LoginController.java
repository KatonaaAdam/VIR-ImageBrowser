package org.example;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm.SaltStyle;
import org.apache.shiro.subject.Subject;
import org.example.model.Router;
import org.sqlite.SQLiteConfig;
import org.example.DB.DBRealm;


public class LoginController implements Initializable {
    public Subject currentUser;
    Router router =new Router();
    Alert a = new Alert(Alert.AlertType.NONE);

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;

    boolean login(String uname,String pwd){


        if (!currentUser.isAuthenticated()) {
            System.out.println("name: "+txtUser.getText()+", pwd: "+txtPassword.getText());
            UsernamePasswordToken token = new UsernamePasswordToken(uname, pwd);
            token.setRememberMe(false);
            try {
                currentUser.login(token);
                return true;
            } catch (UnknownAccountException uae) {
                a.setAlertType(Alert.AlertType.INFORMATION);

                // set content text
                a.setContentText("There is no user with username of " + token.getPrincipal()+"!");

                // show the dialog
                a.show();
                System.out.println("There is no user with username of " + token.getPrincipal());
                return false;
            } catch (IncorrectCredentialsException ice) {
                a.setAlertType(Alert.AlertType.INFORMATION);

                // set content text
                a.setContentText("Password for account " + token.getPrincipal() + " was incorrect!");

                // show the dialog
                a.show();
                System.out.println("Password for account " + token.getPrincipal() + " was incorrect!");
                return false;
            } catch (LockedAccountException lae) {
                a.setAlertType(Alert.AlertType.INFORMATION);

                // set content text
                a.setContentText("The account for username " + token.getPrincipal() + " is locked.  ");

                // show the dialog
                a.show();
                System.out.println("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
                return false;
            }
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
            return false;
        }

        return false;
    }



    @FXML
    void logInAction(ActionEvent event) throws IOException{
        if (login(txtUser.getText(), txtPassword.getText())==true){
            System.out.println("Succesful login "+txtUser.getText()+" !!!");
            router.setPath(event,"/org/example/browser.fxml");
            //App.setRoot("browser");

        }else{

            System.out.println("Login failed!!!");
        }
    }

    @FXML
    void singUpAction(ActionEvent event)  throws IOException{
        router.setPath(event,"/org/example/registration.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PasswordService pwdService = new DefaultPasswordService();
        PasswordMatcher pwdMatcher = new PasswordMatcher();
        pwdMatcher.setPasswordService(pwdService);
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        DBRealm realm = new DBRealm();
        org.sqlite.SQLiteDataSource ds = new org.sqlite.SQLiteDataSource(config);
        ds.setUrl("jdbc:sqlite:src\\main\\resources\\org\\example\\database\\real.db");
        realm.setDataSource(ds);
        realm.setCredentialsMatcher(pwdMatcher);
        realm.setSaltStyle(SaltStyle.COLUMN);
        SecurityManager securityManager = new DefaultSecurityManager(realm);

        SecurityUtils.setSecurityManager(securityManager);
        currentUser = SecurityUtils.getSubject();
    }
}

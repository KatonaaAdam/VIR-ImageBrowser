package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.apache.shiro.authc.credential.PasswordService;
import org.example.dao.UserDAO;
import org.example.dao.UserDAOimp;
import javafx.scene.control.TextField;

import java.io.IOException;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.example.model.Router;


public class RegistrationController {
    Router router =new Router();
    UserDAO dao= new UserDAOimp();
    String pwdSalt;
    Object[] userArr=dao.AllUsername().toArray();
    Alert a = new Alert(Alert.AlertType.NONE);
    @FXML
    private TextField unameText;
    @FXML
    private TextField pwdText;

    public boolean actUserExist(){
        for (int i=0; i<userArr.length; i++)
        {
            if (userArr[i].equals(unameText.getText())){
                return true;
            }
        }
        return false;
    }

    public void okAction(ActionEvent actionEvent) throws IOException{
        PasswordService pwdService = new DefaultPasswordService();
        pwdSalt=pwdService.encryptPassword(pwdText.getText());
       // System.out.println(unameText.getText());
       // System.out.println(pwdText.getText().equals(""));
        if (unameText.getText().equals("") || pwdText.getText().equals("") ){
            a.setAlertType(Alert.AlertType.ERROR);

            // set content text
            a.setContentText("The username field or password field is not filled !!!");

            // show the dialog
            a.show();
        }else if (actUserExist()==true){

                    a.setAlertType(Alert.AlertType.ERROR);

                    // set content text
                    a.setContentText("This username is existed !!!");

                    // show the dialog
                    a.show();

            }else {

        dao.addUser(unameText.getText(),pwdSalt,"");
        dao.addUserRole(unameText.getText(),"basic_1");
        router.setPath(actionEvent,"/org/example/login.fxml");
        System.out.println("Succesfull registration!!!");
        }
    }

    public void backAction(ActionEvent actionEvent)  throws IOException {
        router.setPath(actionEvent,"/org/example/login.fxml");
    }
}

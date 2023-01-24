package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import org.example.dao.UserDAO;
import org.example.dao.UserDAOimp;
import org.example.model.Router;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PermissionListController implements Initializable {
    private UserDAO dao = new UserDAOimp();
    Router router =new Router();
    Object[] roleArr=dao.AllRole().toArray();
    String roleVal;

    Alert a = new Alert(Alert.AlertType.NONE);

    @FXML
    private ComboBox unameComboBox;
    @FXML
    private ComboBox rolenameComboBox;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* role-ok átnevezése és hozzáadása a listához*/
        for (int i=0; i<roleArr.length; i++)
        {
            //System.out.println(roleArr[i].equals("basic_1"));
            if (roleArr[i].equals("basic_1")){
                rolenameComboBox.getItems().add("PNG");
            }else if(roleArr[i].equals("basic_2")){
                rolenameComboBox.getItems().add("JPG");
            }else if(roleArr[i].equals("basic_3")){
                rolenameComboBox.getItems().add("GIF");
            }else if(roleArr[i].equals("basic_4")){
                rolenameComboBox.getItems().add("PNG-JPG");
            }else if(roleArr[i].equals("basic_5")){
                rolenameComboBox.getItems().add("PNG-GIF");
            }else if(roleArr[i].equals("basic_6")){
                rolenameComboBox.getItems().add("JPG-GIF");
            }else {
                rolenameComboBox.getItems().add(roleArr[i]);
            }
        }
        /* nevek hozzáadása a listához*/
        unameComboBox.getItems().addAll(dao.AllUsername());

    }

    public void backAction(ActionEvent actionEvent) throws IOException {
        router.setPath(actionEvent,"/org/example/browser.fxml");
    }

    public void okAction(ActionEvent actionEvent) throws IOException {
        if (unameComboBox.getValue()==null || rolenameComboBox.getValue()==null ){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("The username field or role field is not filled !!!");
            a.show();
        }else{
            switch (rolenameComboBox.getValue().toString()) {
                case "PNG":
                   roleVal="basic_1";
                    break;
                case "JPG":
                    roleVal="basic_2";
                    break;
                case "GIF":
                    roleVal="basic_3";
                    break;
                case "PNG-JPG":
                    roleVal="basic_4";
                    break;
                case "PNG-GIF":
                    roleVal="basic_5";
                    break;
                case "JPG-GIF":
                    roleVal="basic_6";
                    break;
                default:
                    roleVal="admin";
            }
            dao.updateUserRole(roleVal,unameComboBox.getValue().toString());
            router.setPath(actionEvent,"/org/example/browser.fxml");
        }


    }
}

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

public class DeleteUserController implements Initializable {
    private UserDAO dao = new UserDAOimp();
    Router router =new Router();
    Alert a = new Alert(Alert.AlertType.NONE);

    @FXML
    private ComboBox unameComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        unameComboBox.getItems().addAll(dao.AllUsername());
    }



    public void okAction(ActionEvent actionEvent) throws IOException {
        if (unameComboBox.getValue()==null ){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("The username field is not filled !!!");
            a.show();
        }else{

        dao.deleteUserOfUsers(unameComboBox.getValue().toString());
        dao.deleteUserOfUserRoles(unameComboBox.getValue().toString());
        router.setPath(actionEvent,"/org/example/browser.fxml");
        }
    }

    public void backAction(ActionEvent actionEvent) throws IOException {
        router.setPath(actionEvent,"/org/example/browser.fxml");
    }
}

package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.example.DB.DBRealm;
import org.example.model.Router;
import org.sqlite.SQLiteConfig;


import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class BrowserController implements Initializable {

    Router router =new Router();
    File[] listOfFiles;
    File[] listOfFiles2;
    int listOfFilesLength=0;
    int tmp;
    Subject currentUser;
    @FXML
    private ListView<String> imgListView;
    @FXML
    private ImageView imgView;
    @FXML
    private Button updateUser;
    @FXML
    private Button deleteUser;
    @FXML
    private Label imgNameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tmp=-1; // kép pozicióhoz a tmp változó beállítása
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
        realm.setSaltStyle(JdbcRealm.SaltStyle.COLUMN);
        SecurityManager securityManager = new DefaultSecurityManager(realm);

        SecurityUtils.setSecurityManager(securityManager);
        currentUser = SecurityUtils.getSubject();

        //beolvasás
        File folder = new File("src\\main\\resources\\org\\example\\image");
/****************************** Filterek *************************************/
        /******** PNG filter *********/
        FileFilter filterPNG = new FileFilter() {
            public boolean accept(File f) { return f.getName().endsWith("png"); }};
        /******** JPG filter *********/
        FileFilter filterJPG = new FileFilter() {
            public boolean accept(File f) { return f.getName().endsWith("jpg"); }};
        /******** GIF filter *********/
        FileFilter filterGIF = new FileFilter() {
            public boolean accept(File f) { return f.getName().endsWith("gif"); }};
        /******** PNG-JPG filter *********/
        FileFilter filterPNG_JPG = new FileFilter() {
            public boolean accept(File f) { return f.getName().endsWith("png") || f.getName().endsWith("jpg"); }};
        /******** PNG-GIF filter *********/
        FileFilter filterPNG_GIF = new FileFilter() {
            public boolean accept(File f) { return f.getName().endsWith("png") || f.getName().endsWith("gif"); }};
        /******** JPG-GIF filter *********/
        FileFilter filterJPG_GIF = new FileFilter() {
            public boolean accept(File f) { return f.getName().endsWith("jpg") || f.getName().endsWith("gif"); }};
/**********************************************************************************/

        /*****************  Admin  *****************/
        if (currentUser.hasRole("admin")) {
            System.out.println("You are admin");
            updateUser.setVisible(true);
            deleteUser.setVisible(true);
            listOfFiles2 = folder.listFiles();
            listOfFilesLength=listOfFiles2.length;
            //System.out.println(listOfFilesLength);

            for (int i = 0; i < listOfFiles2.length; i++) {
                imgListView.getItems().add(listOfFiles2[i].getName());

            }
        }
        /*****************  PNG  *****************/
        else if (currentUser.hasRole("basic_1")) {
            System.out.println("Your role is basic_1");
            updateUser.setVisible(false);
            deleteUser.setVisible(false);

            listOfFiles = folder.listFiles();
            listOfFilesLength=listOfFiles.length;
            //System.out.println(listOfFilesLength);

            for (int i = 0; i < listOfFiles.length; i++) {
                String[] arrOfStr = listOfFiles[i].getName().split("[.]");

                if(arrOfStr[1].equals("png")) {
                    imgListView.getItems().add(listOfFiles[i].getName());
                    listOfFiles2=folder.listFiles(filterPNG);
                }else{
                    //System.out.println("ez nem png!!!");
                }
            }
            listOfFilesLength=listOfFiles2.length;

        }
        /*****************  JPG  *****************/
        else if (currentUser.hasRole("basic_2")) {
            System.out.println("Your role is basic_2");
            updateUser.setVisible(false);
            deleteUser.setVisible(false);

            listOfFiles = folder.listFiles();
            listOfFilesLength=listOfFiles.length;
            //System.out.println(listOfFilesLength);

            for (int i = 0; i < listOfFiles.length; i++) {
                String[] arrOfStr = listOfFiles[i].getName().split("[.]");

                if(arrOfStr[1].equals("jpg")) {
                    imgListView.getItems().add(listOfFiles[i].getName());
                    listOfFiles2=folder.listFiles(filterJPG);
                }else{
                    //System.out.println("ez nem jpg!!!");
                }
            }
            listOfFilesLength=listOfFiles2.length;
            /*****************  GIF  *****************/
        }else if (currentUser.hasRole("basic_3")) {
            System.out.println("Your role is basic_3");
            updateUser.setVisible(false);
            deleteUser.setVisible(false);

            listOfFiles = folder.listFiles();
            listOfFilesLength=listOfFiles.length;
            //System.out.println(listOfFilesLength);

            for (int i = 0; i < listOfFiles.length; i++) {
                String[] arrOfStr = listOfFiles[i].getName().split("[.]");

                if(arrOfStr[1].equals("gif")) {
                    imgListView.getItems().add(listOfFiles[i].getName());
                    listOfFiles2=folder.listFiles(filterGIF);
                }else{
                    //System.out.println("ez nem jpg!!!");
                }
            }
            listOfFilesLength=listOfFiles2.length;
            /*****************  PNG-JPG  *****************/
        }else if (currentUser.hasRole("basic_4")) {
            System.out.println("Your role is basic_4");
            updateUser.setVisible(false);
            deleteUser.setVisible(false);

            listOfFiles = folder.listFiles();
            listOfFilesLength=listOfFiles.length;
            //System.out.println(listOfFilesLength);

            for (int i = 0; i < listOfFiles.length; i++) {
                String[] arrOfStr = listOfFiles[i].getName().split("[.]");

                if(arrOfStr[1].equals("png") || arrOfStr[1].equals("jpg")) {
                    imgListView.getItems().add(listOfFiles[i].getName());
                    listOfFiles2=folder.listFiles(filterPNG_JPG);
                }else{
                    //System.out.println("ez nem jpg!!!");
                }
            }
            listOfFilesLength=listOfFiles2.length;
            /*****************  PNG-GIF  *****************/
        }else if (currentUser.hasRole("basic_5")) {
            System.out.println("Your role is basic_5 (PNG-GIF)");
            updateUser.setVisible(false);
            deleteUser.setVisible(false);

            listOfFiles = folder.listFiles();
            listOfFilesLength=listOfFiles.length;
            //System.out.println(listOfFilesLength);

            for (int i = 0; i < listOfFiles.length; i++) {
                String[] arrOfStr = listOfFiles[i].getName().split("[.]");

                if(arrOfStr[1].equals("png") || arrOfStr[1].equals("gif")) {
                    imgListView.getItems().add(listOfFiles[i].getName());
                    listOfFiles2=folder.listFiles(filterPNG_GIF);
                }else{
                    //System.out.println("ez nem jpg!!!");
                }
            }
            listOfFilesLength=listOfFiles2.length;
            /*****************  JPG-GIF  *****************/
        }else if (currentUser.hasRole("basic_6")) {
            System.out.println("Your role is basic_6 (JPG-GIF)");
            updateUser.setVisible(false);
            deleteUser.setVisible(false);

            listOfFiles = folder.listFiles();
            listOfFilesLength=listOfFiles.length;
            //System.out.println(listOfFilesLength);

            for (int i = 0; i < listOfFiles.length; i++) {
                String[] arrOfStr = listOfFiles[i].getName().split("[.]");

                if(arrOfStr[1].equals("jpg") || arrOfStr[1].equals("gif")) {
                    imgListView.getItems().add(listOfFiles[i].getName());
                    listOfFiles2=folder.listFiles(filterJPG_GIF);
                }else{
                    //System.out.println("ez nem jpg!!!");
                }
            }
            listOfFilesLength=listOfFiles2.length;
        }else{
            System.out.println("Your role isn't existed!");
        }
/******************************* Actions ************************************/
    }

    public void nextAction(ActionEvent actionEvent)throws FileNotFoundException {
        if (tmp<listOfFilesLength-1){
        tmp++;
        imgNameLabel.setText(listOfFiles2[tmp].getName());
        Image image = new Image(new FileInputStream(listOfFiles2[tmp]));
        imgView.setImage(image);
        }else{
           // System.out.println("A listOfFilesLength nagyobb mint ahány kép van!");
        }
    }

    public void backAction(ActionEvent actionEvent)throws FileNotFoundException  {
        if(tmp>0){
        tmp--;
        imgNameLabel.setText(listOfFiles2[tmp].getName());
        Image image = new Image(new FileInputStream(listOfFiles2[tmp]));
        imgView.setImage(image);

        }else{
           // System.out.println("A listOfFilesLength kisebb mint 0!");
        }
    }

    public void logoutAction(ActionEvent actionEvent) throws IOException {
        currentUser.logout();
        router.setPath(actionEvent,"/org/example/login.fxml");
    }

    public void permissionUserAction(ActionEvent actionEvent)throws IOException {
        router.setPath(actionEvent,"/org/example/permissionList.fxml");


    }

    public void deleteUserAction(ActionEvent actionEvent)throws IOException {
        router.setPath(actionEvent,"/org/example/deleteUser.fxml");
    }
}

package com.example.cafeautomation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button cancelButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Label loginLabel;

    public void loginButtonOnAction(ActionEvent e) throws IOException {
        if(usernameTextField.getText().isBlank()&& passwordPasswordField.getText().isBlank()){
            loginLabel.setText("Please enter Username and Password!");

        }
        else if (validateLogin()){
            getStaff();
            loginLabel.setText(Staff.activeStaff.name);


            root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));//yeni sahneye gecis
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else         loginLabel.setText("Invalid login. Please try again.");

    }

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public boolean validateLogin(){
        boolean result = false;

        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectDB= connectNow.getConnection();

    String verifyLogin="SELECT count(1) from staff where username='"+usernameTextField.getText()+"' and password='"+passwordPasswordField.getText()+"'";
    try {
        Statement statement= connectDB.createStatement();
        ResultSet queryResult= statement.executeQuery(verifyLogin);
        while (queryResult.next()) {
            result = queryResult.getInt(1) == 1;
        }
        queryResult.close();
        statement.close();
        connectDB.close();

    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
    }

    public void getStaff(){    //DB'den staff bilgilerini Staff static sinifina aktarma
        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectDB= connectNow.getConnection();

        String getStaff="SELECT * from staff where username='"+usernameTextField.getText()+"'";

        try {
            Statement statement= connectDB.createStatement();
            ResultSet queryResult= statement.executeQuery(getStaff);
            queryResult.next();

            int id = queryResult.getInt("id");
            int jobId= queryResult.getInt("staffJobId");
            String username = queryResult.getString("username");
            String password = queryResult.getString("password");
            String name = queryResult.getString("name");
            String surname = queryResult.getString("surname");
            Staff.getActiveStaff(id,jobId,username,password,name,surname);
            queryResult.close();
            statement.close();
            connectDB.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


}
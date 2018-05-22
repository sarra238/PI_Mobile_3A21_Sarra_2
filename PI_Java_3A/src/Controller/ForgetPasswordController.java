/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class ForgetPasswordController implements Initializable {
    @FXML
    private TextField username;
    
    @FXML
    private TextField email;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button nvmdp;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML
    public void MdpOublié(ActionEvent event) throws IOException
    {
        boolean b,c;
        User U = new User();
        U.setUsername(username.getText());
        U.setEmail(email.getText());
        U.setPassword(mdp.getText());
        UserService Us=new UserService();
        b=Us.MdpO(U); 
        c=(U.getUsername().isEmpty()||U.getEmail().isEmpty()||U.getPassword().isEmpty());
        if(c==true)
        {
            nvmdp.setDisable(c);
        }
        else
        {
            nvmdp.setDisable(false);
        if(b==true)
        {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Connexion!");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else
        {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("AlertLogin.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Alert!");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        }
    }
}

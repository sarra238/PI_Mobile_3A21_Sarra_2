/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class UserLoginController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink inscri;
    @FXML
    private Hyperlink MdpOublié;

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
    public void Login(ActionEvent event) throws IOException, SQLException
    {
         String b;
        
          UserService us=new UserService();
        
        String pass=us.RechercherUsertByPass(username.getText());
        if(BCrypt.checkpw(mdp.getText(),pass)) {
            /*System.out.println(pass);
            System.out.println(mdp.getText());
            System.out.println(BCrypt.hashpw(mdp.getText(), BCrypt.gensalt()));*/
        UserService Us=new UserService();
        User U = new User();
          U.setUsername(username.getText());
          U.setPassword(pass);
        b=Us.login(U);
        if(!"hi".equals(b)){
            switch (b) {
                case "a:1:{i:0;s:11:"+"ROLE_CLIENT"+";}":
                    {
                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("HomeC.fxml"));
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Home!");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        break;
                    }
                case "a:1:{i:0;s:12:"+"ROLE_ARTISAN"+";}":
                    {
                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Home!");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        break;
                    }
            }
         }}
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
    @FXML
    public void Inscri(ActionEvent event) throws IOException
    {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("UserSignIn.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Inscription!");
        primaryStage.setScene(scene);
        primaryStage.show();    
    }
    @FXML
    public void MdpOublié(ActionEvent event) throws IOException
    {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ForgetPassword.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Mot de passe oublié !");
        primaryStage.setScene(scene);
        primaryStage.show();    
    }
}

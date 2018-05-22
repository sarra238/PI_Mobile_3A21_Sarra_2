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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class UserSignInController implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
    @FXML
    private TextField adresse;
    @FXML
    private RadioButton femme;
    @FXML
    private RadioButton homme;
    @FXML
    private TextField email;
    @FXML
    private RadioButton client;
    @FXML
    private RadioButton artisan;
    @FXML
    private TextField tel;
    @FXML
    private TextField username;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button btn;
    @FXML
    private ToggleGroup gender;
    @FXML
    private ToggleGroup roles;
    @FXML
    private Hyperlink cnx;

        String rclient="a:1:{i:0;s:11:"+"ROLE_CLIENT"+";}";
        
        String rartisan="a:1:{i:0;s:12:"+"ROLE_ARTISAN"+";}";
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
    public void Inscri(ActionEvent event) throws IOException
    {
        
        User U = new User();
        U.setFname(nom.getText());
        U.setLname(prenom.getText());
      //  U.setAge((int) Float.parseFloat(age.getText()));
       // if(femme.isSelected()){ U.setGender(femme.getText());} else {U.setGender(homme.getText());}
        U.setAddress(adresse.getText());
        U.setEmail(email.getText());
        if(client.isSelected()){ 
            U.setRole("a:1:{i:0;s:11:"+"ROLE_CLIENT"+";}");}
        else {U.setRole("a:1:{i:0;s:12:"+"ROLE_ARTISAN"+";}");}
      //  U.setPhoneNumber((int) Float.parseFloat(tel.getText()));
        U.setUsername(username.getText());
        U.setPassword(mdp.getText());
       /* boolean b=(U.getAddress().isEmpty()||U.getAge()==0||U.getEmail().isEmpty()||U.getFname().isEmpty()||U.getGender().isEmpty()||U.getLname().isEmpty()||U.getPassword().isEmpty()||U.getPhoneNumber()==0||U.getRole().isEmpty()||U.getUsername().isEmpty());
        if(b==true)
        {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AlertLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Alert!");
        primaryStage.setScene(scene);
        primaryStage.show(); 
        }
        else
        {*/
           
        UserService Us = new UserService();
        Us.signIn(U);
            System.out.println(U.getRole());
        if(U.getRole().equals("a:1:{i:0;s:12:"+"ROLE_ARTISAN"+";}"))
        {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Home!");
        primaryStage.setScene(scene);
        primaryStage.show();
        }
        else
        {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("HomeC.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Home!");
        primaryStage.setScene(scene);
        primaryStage.show();
        }
  //      }
    }
    @FXML
    public void Login(ActionEvent event) throws IOException
    {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login!");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }
    
}

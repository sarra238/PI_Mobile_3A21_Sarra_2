/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_java_3a;

import Entities.User;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class PI_Java_3A extends Application {
    private static PI_Java_3A instance;
    private User loggedUser;
    
        public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public PI_Java_3A (){
         instance = this;
    } 
    public static PI_Java_3A getInstance() {
        return instance;
        
    }
    @Override
    public void start(Stage primaryStage) { 
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/Controller/UserLogin.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Connexion!");
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("Login");
        } 
        catch (IOException ex) {
            System.out.println("Erreur " + ex.getMessage());
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args); 
    }
    
}

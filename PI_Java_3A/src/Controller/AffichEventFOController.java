/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.EvenementServices;

/**
 * FXML Controller class
 *
 * @author lv
 */
public class AffichEventFOController implements Initializable {
    public static int idE;
    @FXML
    private TableColumn<?, ?> nom_Evenement;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> localisation;
    @FXML
    private TableColumn<?, ?> dated;
    @FXML
    private TableColumn<?, ?> datef;
    @FXML
    private TableColumn<?, ?> image;
    @FXML
    private Button delete;
    @FXML
    private Button stat;
    @FXML
    private Button add;
    @FXML
    private Button modifier;
    @FXML
    private TableView<?> table_view;
    public static Evenement d;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             EvenementServices v =  new  EvenementServices() ;
            
            ArrayList arrayList = (ArrayList) v.AfficherAllEvenement();
            ObservableList observablelist = FXCollections.observableArrayList(arrayList);
            table_view.setItems(observablelist);
            nom_Evenement.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
            description.setCellValueFactory(new PropertyValueFactory<>("Description"));
            dated.setCellValueFactory(new PropertyValueFactory<>("dateDeb"));
            datef.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            localisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
         
            

        
        
    

            // TODO
          /*
   UserService u = new UserService();
            if (pi_java_3a.PI_Java_3A.getInstance().getLoggedUser().getRole().equals("Artisan")){
                delete.set();
                
            }  */ }    
    @FXML
    private void delete(ActionEvent event) {
        ObservableList<Evenement> r,f;
        EvenementServices Ann=new EvenementServices();
        f=(ObservableList<Evenement>) table_view.getItems();
        r=(ObservableList<Evenement>) table_view.getSelectionModel().getSelectedItems();
        for(Evenement A : r){
            Ann.SupprimerEvenement(A);
            f.remove(A);
        }
    }
    @FXML
    private void stat(ActionEvent event)throws IOException{   
      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("stat.fxml"));
      Scene scene = new Scene(root);    
      primaryStage.setTitle("statistiques");
      primaryStage.setScene(scene);
      primaryStage.show();
     }
    @FXML
    private void addEvent(ActionEvent event)throws IOException {    
     Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Parent root = FXMLLoader.load(getClass().getResource("evenement.fxml"));
     Scene scene = new Scene(root);
     primaryStage.setTitle("statistiques");
     primaryStage.setScene(scene);
     primaryStage.show();
    }
    @FXML
    private void modifier(ActionEvent event) throws IOException {
      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     FXMLLoader loader = new FXMLLoader();
     loader.setLocation(getClass().getResource("modifier.fxml"));
     Parent root = loader.load();      
     Scene scene = new Scene(root);
     ObservableList<Evenement> r,f;
     EvenementServices Ann=new EvenementServices();
     f=(ObservableList<Evenement>) table_view.getItems();
     r=(ObservableList<Evenement>) table_view.getSelectionModel().getSelectedItems();
     for(Evenement A : r){
        ModifierController controller = loader.getController();
        controller.iData(A);
     }  
     primaryStage.setTitle("modifier Evenement");
     primaryStage.setScene(scene);
     primaryStage.show();    
    } 
    @FXML
    private void details(ActionEvent event) throws IOException { Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     FXMLLoader loader = new FXMLLoader();
     loader.setLocation(getClass().getResource("EventDetails.fxml"));
     Parent root = loader.load();      
     Scene scene = new Scene(root);
     ObservableList<Evenement> r,f;
     EvenementServices Ann=new EvenementServices();
     f=(ObservableList<Evenement>) table_view.getItems();
     r=(ObservableList<Evenement>) table_view.getSelectionModel().getSelectedItems();
     for(Evenement A : r){
         EventDetailsController controller = loader.getController();
        controller.iData(A);
        d=A;
        idE=A.getId();
        
     }  
     primaryStage.setTitle("details");
     primaryStage.setScene(scene);
     primaryStage.show();    
    } 
    
}

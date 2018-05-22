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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.EvenementServices;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author lv
 */
public class EventFoController implements Initializable {


    @FXML
    private TableView<Evenement> table_view;
    @FXML
    private TableColumn<Evenement, String> nom_Evenement;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> type;
    @FXML
    private TableColumn<Evenement, String> localisation;
    @FXML
    private AnchorPane liste;
    @FXML
    private TableColumn<?, ?> dated;
    @FXML
    private TableColumn<?, ?> datef;
    @FXML
    private Button delete;
    @FXML
    private Button stat;
    @FXML
    private Button add;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
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
     f=table_view.getItems();
     r=table_view.getSelectionModel().getSelectedItems();
     for(Evenement A : r){
        ModifierController controller = loader.getController();
        controller.iData(A);
     }  
     primaryStage.setTitle("modifier Evenement");
     primaryStage.setScene(scene);
     primaryStage.show();    
    }     
}


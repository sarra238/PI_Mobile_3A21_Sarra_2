/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Annonce;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.AnnonceServices;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class AnnoncesArtisanController implements Initializable {
    @FXML
    private TableView<Annonce> listAnnonce;
    @FXML
    private TableColumn<Annonce, String> nomAnnonce;
    @FXML
    private TableColumn<Annonce, String> type;
    @FXML
    private TableColumn<Annonce, Float> prix;
    @FXML
    private TableColumn<Annonce, String> description;
    @FXML
    private Button Ajout;
    @FXML
    private Button suppBtn;
    @FXML
    private Button StatBtn;
    @FXML
    private Button ModifBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AnnonceServices Ann=new AnnonceServices();
        ArrayList A= (ArrayList) Ann.AfficherAllAnnonce();
        ObservableList ob=FXCollections.observableArrayList(A);
        listAnnonce.setItems(ob);
        nomAnnonce.setCellValueFactory(new PropertyValueFactory<>("nomAnnonce"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        prix.setCellValueFactory(new PropertyValueFactory<>("PrixReducton"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        listAnnonce.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }  
    @FXML
    public void delete(){
        ObservableList<Annonce> r,f;
        AnnonceServices Ann=new AnnonceServices();
        f=listAnnonce.getItems();
        r=listAnnonce.getSelectionModel().getSelectedItems();
        for(Annonce A : r){
            Ann.SupprimerAnnonceA(A);
            f.remove(A);
        }
    }

    @FXML
    private void AjouterAnn(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AjoutAnnonce.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonce!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void StatType(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("StatAnnonce.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonce!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void Modif(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifierAnnonce.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ObservableList<Annonce> r,f;
        AnnonceServices Ann=new AnnonceServices();
        f=listAnnonce.getItems();
        r=listAnnonce.getSelectionModel().getSelectedItems();
        for(Annonce A : r){
        ModifierAnnonceController controllerModifA = loader.getController();
        controllerModifA.DATA(A);
        }
        primaryStage.setTitle("Modifier Annonce!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}

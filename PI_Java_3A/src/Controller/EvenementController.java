/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.EvenementServices;
import utils.Utils;
import Controller.UserLoginController;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import static services.UserService.conn;


/**
 * FXML Controller class
 *
 * @author lv
 */
public class EvenementController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private ToggleGroup type;
    @FXML
    private TextField desc;
    @FXML
    private TextField nomE;
    @FXML
    private TextField localisation;
    @FXML
    private DatePicker Datefin;   
    @FXML
    private RadioButton formation;
    @FXML
    private RadioButton Exposition;
    @FXML
    private RadioButton autres;
    @FXML
    private Button filebtn;
    @FXML
    private ImageView imgEvent;
    
    private FileChooser fc;
    private File f;
    private Image image;
    private String uuid;
    @FXML
    private DatePicker date;
    
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
    private void ajouterEvenement(ActionEvent event) throws IOException {
        Evenement e = new Evenement( nomE.getText(),desc.getText(),date.getValue().toString(),Datefin.getValue().toString(),localisation.getText());
        if (formation.isSelected()) {
            e.setType(formation.getText());
        }      
        else if (Exposition.isSelected()) {
            e.setType(Exposition.getText());
        }   
        else if  (autres.isSelected()) {
            e.setType(autres.getText());        
        }  
      
        e.setNomImg(uuid);
        e.setUserId(conn);
        EvenementServices s = new EvenementServices();
        s.AjouterEvenement(e);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("affich.fxml"));
        Scene scene = new Scene(root);        
        primaryStage.setTitle("Evenement!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void fileChooser(ActionEvent event) throws IOException {
        fc = new FileChooser();
        f = fc.showOpenDialog(null);
        if(f!=null)
        {
            uuid=UUID.randomUUID().toString().replaceAll("--","")+".jpg";
            
            image=new Image(f.toURI().toString(),100,150,true,true);
            imgEvent.setImage(image); 
            Utils u =new Utils();
         //   String Emp ="D:\\Dossiers\\ESPRIT\\3A21\\2éme semestre\\PIDEV\\Sprint Java\\S\\PI_Java_3A\\src\\imageBDEvent\\"+uuid;
         
         String Emp ="C:\\wamp64\\www\\SoukI\\web\\images2\\"+uuid;
            u.CopyImage(Emp, f.toPath().toString());
        }    
    }
}
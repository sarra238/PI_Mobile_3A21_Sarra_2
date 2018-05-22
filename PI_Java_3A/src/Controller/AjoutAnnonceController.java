/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Annonce;
//import com.sun.javafx.util.Utils;
import utils.Utils;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.AnnonceServices;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class AjoutAnnonceController implements Initializable {
    @FXML
    private TextField nomAnnonce;
    @FXML
    private TextField PrixReducton;
    @FXML
    private TextArea description;
    @FXML
    private Button AjoutBtn;
    @FXML
    private RadioButton Promotion;
    @FXML
    private RadioButton Other;
    @FXML
    private ToggleGroup Type;
    @FXML
    private Label prixLabel;
    @FXML
    private Button fileBtn;
    @FXML
    private TextField fileText;
    @FXML
    private ImageView imageAnn;
    
    private FileChooser fc;
    private File f;
    private Image image;
    private String uuid;
    
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
    private void fileChooser(ActionEvent event) throws IOException {
        fc = new FileChooser();
        f = fc.showOpenDialog(null);
        if(f!=null)
        {
            uuid=UUID.randomUUID().toString().replaceAll("--","")+".jpg";
            
            image=new Image(f.toURI().toString(),100,150,true,true);
            imageAnn.setImage(image); 
            Utils u =new Utils();
            String Emp ="D:\\Dossiers\\ESPRIT\\3A21\\2éme semestre\\PIDEV\\Sprint Java\\S\\PI_Java_3A\\src\\imageBDAnn\\"+uuid;
            u.CopyImage(Emp, f.toPath().toString());
        }    
    }
        
    @FXML
    private void AjoutAnnonce(ActionEvent event) throws IOException
    {
    Annonce A=new Annonce();
        A.setNomAnnonce(nomAnnonce.getText());
        if(Promotion.isSelected()){
            A.setType(Promotion.getText());
            A.setPrixReducton(Float.parseFloat(PrixReducton.getText()));
            PrixReducton.setEditable(true);
        }
        else if(Other.isSelected()) { 
            A.setType(Other.getText());
            PrixReducton.setEditable(false);
            PrixReducton.setMouseTransparent(true);
        }
        A.setDescription(description.getText());
        A.setImage(uuid);
        AnnonceServices Ann=new AnnonceServices();
        boolean b=(A.getDescription().isEmpty()||A.getNomAnnonce().isEmpty()||A.getType().isEmpty());
        if(b==true)
        {
            AjoutBtn.setDisable(b);
        }
        else
        {
        Ann.AjouterAnnonce(A);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AnnoncesArtisan.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonces!");
        primaryStage.setScene(scene);
        primaryStage.show();    
        }
    }
    
}

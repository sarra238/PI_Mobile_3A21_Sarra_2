/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Annonce;
import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.AnnonceServices;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class ModifierAnnonceController implements Initializable {
    @FXML
    private Label prixLabel;
    @FXML
    private TextField nomAnnonce;
    @FXML
    private TextField PrixReducton;
    @FXML
    private TextArea description;
    @FXML
    private Button ModifBtn;
    @FXML
    private RadioButton Promotion;
    @FXML
    private ToggleGroup Type;
    @FXML
    private RadioButton Other;
    @FXML
    private Button fileBtn;
    @FXML
    private TextField fileText;
    @FXML
    private ImageView imageAnn;
    @FXML
    private TextField id;
    
    private Image image;
    private File f;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      // TODO
    }    

    public void DATA(Annonce A)
    {
        id.setText(Integer.toString(A.getId()));
        nomAnnonce.setText(A.getNomAnnonce());
        description.setText(A.getDescription());
        PrixReducton.setText(Float.toString((float) A.getPrixReducton()));
        switch (A.getType()) {
            case "Other":
                Other.setSelected(true);
                break;
            case "Promotion":
                Promotion.setSelected(true);
                break;
        }
        f=new File("D:\\Dossiers\\ESPRIT\\3A21\\2éme semestre\\PIDEV\\Sprint Java\\S\\PI_Java_3A\\src\\imageBDAnn\\"+A.getImage());
        Image img=new Image(f.toURI().toString());
        imageAnn.setImage(img);
    }
    @FXML
    private void ModifierAnnonce(ActionEvent event) throws IOException
    {
    Annonce A=new Annonce();
        A.setId(Integer.parseInt(id.getText()));
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
        AnnonceServices Ann=new AnnonceServices();
        boolean b=(A.getDescription().isEmpty()||A.getNomAnnonce().isEmpty()||A.getType().isEmpty());
        if(b==true)
        {
            ModifBtn.setDisable(b);
        }
        else
        {
        Ann.ModifierAnnonce(A);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AnnoncesArtisan.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonces!");
        primaryStage.setScene(scene);
        primaryStage.show();    
        }
        
    }

    @FXML
    private void fileChooser(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AffichEventFOController.d;
import static Controller.AffichEventFOController.idE;
import Entities.Evenement;
import Entities.User;
import Entities.commentaire;
import Entities.particEv;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.EvenementServices;
import services.UserService;
import static services.UserService.conn;
import services.commServices;
import services.partEvServices;
import utils.mailU;

/**
 * FXML Controller class
 *
 * @author lv
 */
public class EventDetailsController implements Initializable {

    @FXML
    private TextField type;
    @FXML
    private TextField localisation;
    @FXML
    private TextField Datefin;
    @FXML
    private TextField DateDeb;
    @FXML
    private TextField desc;
    @FXML
    private TextField nomE;
    @FXML
    private TextField id;
    @FXML
    private ImageView imgEvent;
 private Image image;
    private File f;
    @FXML
    private ToggleGroup part;
    @FXML
    private RadioButton nint;
    @FXML
    private RadioButton par;
    @FXML
    private RadioButton in;
   
    @FXML
    private TextField Eid;
    @FXML
    private Button mail;
    @FXML
    private Button com;
    @FXML
    private TableColumn<commentaire,String> list;
    @FXML
    private TableView<commentaire> table;
    @FXML
    private TextField comment;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           /*  commServices v =  new  commServices() ;
            
            ArrayList arrayList = (ArrayList) v.AfficherAllComm(d.getId());
            ObservableList observablelist = FXCollections.observableArrayList(arrayList);
            table.setItems(observablelist);
            list.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
            
         */
          
        
    }    

   public void iData(Evenement e) 
   {
       
     partEvServices s = new partEvServices();
   
  
         
         if(s.Count(e.getId(), conn)== 0 ){
             
       
id.setText(Integer.toString(e.getId()));
nomE.setText(e.getNomEvenement());
        desc.setText(e.getDescription());
localisation.setText(e.getLocalisation());
DateDeb.setText(e.getDateDeb());
Datefin.setText(e.getDateFin());
       type.setText( e.getType()) ;

        f=new File("C:\\wamp64\\www\\SoukI\\web\\images2\\"+e.getNomImg());
        Image img=new Image(f.toURI().toString());
        imgEvent.setImage(img);
        
} 
   
         else {
             id.setText(Integer.toString(e.getId()));
nomE.setText(e.getNomEvenement());
        desc.setText(e.getDescription());
localisation.setText(e.getLocalisation());
DateDeb.setText(e.getDateDeb());
Datefin.setText(e.getDateFin());
       type.setText( e.getType()) ;
 
        f=new File("C:\\wamp64\\www\\SoukI\\web\\images2\\"+e.getNomImg());
        Image img=new Image(f.toURI().toString());
        imgEvent.setImage(img);
        if(s.RechercherParById(e.getId(), conn).getType().equals("participé(e)")){
            par.setSelected(true);
            in.setSelected(false);
                nint.setSelected(false);
           
        }
        else if(s.RechercherParById(e.getId(), conn).getType().equals("n'est pas interessé(e)")){
           nint.setSelected(true);
           par.setSelected(false);
                in.setSelected(false);
        }
          else if(s.RechercherParById(e.getId(), conn).getType().equals("interessé(e)")){
           in.setSelected(true);
           par.setSelected(false);
                nint.setSelected(false);
        }
          Eid.setText(Integer.toString(s.RechercherId(e.getId(), conn)));  
           
         }
         commServices v =  new  commServices() ;
            System.out.println(e.getId());
            ArrayList arrayList = (ArrayList) v.AfficherAllComm(e.getId());
            ObservableList observablelist = FXCollections.observableArrayList(arrayList);
            table.setItems(observablelist);
            list.setCellValueFactory(new PropertyValueFactory<>("comm"));
      
        
   }

    @FXML
    private void creatpart(ActionEvent event) throws IOException {
          partEvServices s = new partEvServices();
          particEv e = new particEv();
        if(s.Count(d.getId(), conn)== 0 ){
        
        if (in.isSelected()) {
            e.setType(in.getText());
        }      
        else if (nint.isSelected()) {
            e.setType(nint.getText());
        }   
        else if  (par.isSelected()) {
            e.setType(par.getText());        
        }  
   e.setUserId(conn);
        e.setIdEv(d.getId());
      
        s.AjouterPart(e);
          
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(EventDetailsController.this.getClass().getResource("EventDetails.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                EventDetailsController controller = loader.getController();
                controller.iData(d);
                primaryStage.setTitle("Details Event");
                primaryStage.setScene(scene);
                primaryStage.show();
        }
        else if (s.Count(d.getId(), conn)!= 0) {
        part.getSelectedToggle().selectedProperty().addListener( (obs, oldSelection, newSelection) -> {
            try {
                
                if( newSelection!=null) 
                {
                    if (par.isSelected()){
                        in.setSelected(false);
                        nint.setSelected(false);
                  
                        particEv v =  s.RechercherParById(d.getId(), conn);
                           v.setId(Integer.parseInt(Eid.getText()));
                           System.out.println(v.getType());
                        v.setType("participé(e)");
                        
                        System.out.println("bbd");
                    
                        s.ModifierPart(v);
                         Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(EventDetailsController.this.getClass().getResource("EventDetails.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                EventDetailsController controller = loader.getController();
                controller.iData(d);
                primaryStage.setTitle("Details Event");
                primaryStage.setScene(scene);
                primaryStage.show();
                    }
                    else if (nint.isSelected()){
                        in.setSelected(false);
                        par.setSelected(false);
               
                        System.out.println("bb");
                        particEv v =  s.RechercherParById(d.getId(), conn);
                        
                         v.setId(Integer.parseInt(Eid.getText()));
                        v.setType("n'est pas interessé(e)");
                        
                        s.ModifierPart(v);
                     Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(EventDetailsController.this.getClass().getResource("EventDetails.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                EventDetailsController controller = loader.getController();
                controller.iData(d);
                primaryStage.setTitle("Details Event");
                primaryStage.setScene(scene);
                primaryStage.show();
                    }
                    else if(in.isSelected()) {
                        par.setSelected(false);
                        nint.setSelected(false);
             
                        particEv v =  s.RechercherParById(d.getId(), conn);
                        System.err.println(conn);
                               v.setId(Integer.parseInt(Eid.getText()));  
                        v.setType("interessé(e)");
                        
                        System.out.println("2bb");
                        s.ModifierPart(v);
                         Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(EventDetailsController.this.getClass().getResource("EventDetails.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                EventDetailsController controller = loader.getController();
                controller.iData(d);
                primaryStage.setTitle("Details Event");
                primaryStage.setScene(scene);
                primaryStage.show();
                    }
                
                
               
                }
                    else {
                        System.out.println("ff"); 
                   }
                
            }catch (Exception ex) {
             
            }
        });
           
    }
    }
String m;
    @FXML
    private void mail(ActionEvent event) throws SQLException  {

      User u=new User();
     UserService us = new UserService();
      u = us.RechercherUsertById(conn);
       /* ReserEvServices rs = new ReserEvServices();
        ReserEv r = new ReserEv();
        r.setIdEv(d.getId());
        r.setUserId(conn);
        r.setNom(u.getFname());
        r.setPrenom(u.getLname());
        r.setMail(u.getEmail());
        r.setTel(u.getPhoneNumber());
      */          
       final String username = "benhadjkhouloud@gmail.com";
    final String password = "gazafil<3";

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
props.put("mail.smtp.ssl.trust", "*");
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    try {

        Message message = new MimeMessage(session);
        System.out.println(u.getEmail());
        message.setFrom(new InternetAddress("benhadjkhouloud@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(u.getEmail()));
        message.setSubject("Testing Subject");
   /*    if (u.getGender()=="Femme"){
            m = "Madame";}
       else{ m = "Monsieur";}*/
       Evenement e = new Evenement();
        EvenementServices es = new EvenementServices();
      Evenement ER = es.RechercherEvenementById(d.getId());
       u = us.RechercherUsertById(d.getUserId());
        System.out.println(d.getUserId());
        message.setText("Bonjour,"+"\n"+ u.getFname()+ u.getLname()+
       " \n Vous etes inscrit a la formation "+ER.getNomEvenement()+"le "+ER.getDateDeb()+
        "\n pour valider votre inscription voici le numero de telephone du responsabe a la formation"+u.getPhoneNumber());

        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
    }
   /* String   receipient= (u.RechercherUsertById(conn)).getEmail();
        System.out.println(receipient+d.getNomEvenement()+conn);
        User s = u.RechercherUsertById(conn);
        
    boolean b=    mailU.sendMail(d , s);
      System.out.println(receipient+d.getNomEvenement()+conn+b);
    }
*/
    @FXML
    private void commenter(ActionEvent event) throws IOException {
        commServices cs = new commServices();
        commentaire  c = new commentaire();
         c.setUserId(conn);
        c.setIdEv(d.getId());
      c.setComm(comment.getText());
        cs.AjouterComm(c);
          
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(EventDetailsController.this.getClass().getResource("EventDetails.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                EventDetailsController controller = loader.getController();
                controller.iData(d);
                primaryStage.setTitle("Details Event");
                primaryStage.setScene(scene);
                primaryStage.show();
    }
}

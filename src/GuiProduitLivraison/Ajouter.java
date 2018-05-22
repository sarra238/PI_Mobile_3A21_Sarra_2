
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiProduitLivraison;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author 3D-Artist
 */
public class Ajouter {
    private Resources theme;
    private Form current;
    Button btn;
    //TextField matricule;
    //GenericSpinner marque;
    TextField quantite;
    
    TextField nom;
    TextField prenom;
   
   Form f;
 
            
    
    public Ajouter(){
          
           f= new Form("confirmer commande", BoxLayout.y()); 
          Command back;
        back = new Command("Back"){
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Going back now");
                Affichagecmd cmd=new Affichagecmd();
                cmd.getF().show();
            }
            
        };
                
                f.getToolbar().setBackCommand(back);
          UIBuilder ui = new UIBuilder();

         btn = new Button("confirmer");
       
      
        TextField nom=new TextField("","nom");
     
       TextField prenom=new TextField("","prenom");
        TextField adresse=new TextField("","adresse");
        TextField idProduits=new TextField("","idProduits");
          TextField quantite=new TextField("","quantite");
     //   TextField description=new TextField("","Description");
        
    
        
        Label labelquantite = new Label("quantite");
        Label labelNom = new Label("Nom");
        Label labelprenom = new Label("prenom");
         Label labeladresse = new Label("adresse");
         Label labelidProduits = new Label("idProduits");
        

        
      
        f.add(labelNom);
        f.add(nom);
        f.add(labelprenom );
        f.add(prenom);
         f.add(labeladresse );
          f.add(adresse);
            f.add(labelidProduits );
          f.add(idProduits);
         f.add(labelquantite);
        f.add(quantite);
        f.add(btn);
        
        
        

       f.show();
        
        
         btn.addActionListener((ActionListener) (ActionEvent evt) -> {
             //***************Start of button action performed
             
             ConnectionRequest con;
             con = new ConnectionRequest(){
                 LinkedHashMap h;
                 LinkedHashMap content = new LinkedHashMap();
                 
                 @Override
                 protected void postResponse() {
                     System.err.println(h);
                     System.out.println(h.get("status"));
                     // boolean status = Boolean.parseBoolean( h.get("status").toString());
                     // //Afficher dialog success
                     //  content = (LinkedHashMap) h.get("content");
                     for (Object object : content.entrySet()) {
                         Map.Entry me = (Map.Entry) object;
                         System.out.println(me.getValue());
                     }
                     
                     
                     Dialog.show("Succée", "commande envoyer", "OK", null);
                     
                     
                 }
                 
                 
                 @Override
                 protected void readResponse(InputStream input) throws IOException {
                     JSONParser parser = new JSONParser();
                     h = (LinkedHashMap) parser.parseJSON(new InputStreamReader(input));
                     // h.show();
                     
                 }
                 
             };
             con.setPost(false);
             con.setUrl("http://localhost/SoukI/web/app_dev.php/Produit/aaa/"+ nom.getText() + "/" + prenom.getText()+ "/" + adresse.getText()+ "/" + idProduits.getText()+ "/" + quantite.getText());
             
             System.out.println(con.getUrl());
             
             InfiniteProgress progress = new InfiniteProgress();
             Dialog dlg = progress.showInifiniteBlocking();
             con.setDisposeOnCompletion(dlg);
             
             
             NetworkManager.getInstance().addToQueueAndWait(con);
             
             //con.add(h);
             //con.add( btnajout);
              
             //  f.add(con);
             //   f.add(con);
           } //  f.getF().show();
           );
         
         
         
         
     
                 
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{Affichagecmd h=new Affichagecmd();
          h.getF().show();
          });
         
   
           
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}


                 
            
       
                 

           

            
        
    
        
        
    
      






/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiProduitLivraison;

import Entities.livraison;
import Services.CRUDPrLiv;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;



/**
 *
 * @author haythem
 */
public class livraisonartisant {
    
    Form f;
    SpanLabel lb;
     Button btnsuup,confirmer, btnmap, pdf,capture;
  Resources res;
  float somme;


           Label somm,mer;
    public livraisonartisant() {
        
        f = new Form();
        Container conk = new Container(BoxLayout.y());
        CRUDPrLiv c =new CRUDPrLiv();
         ArrayList<livraison> lis1=c.getallliv();
           for (int i = 0; i < lis1.size(); i++) {
  
               Container con = new Container(BoxLayout.y());
               lb=new SpanLabel("");
                 int z =lis1.get(i).getId();
            
               
                  lb.setText(lis1.get(i).getNom());
                   Label l =new Label(lis1.get(i).getPrenom());
                   Label l4 =new Label(Integer.toString(lis1.get(i).getQuantite()));
                   Label l7 =new Label(lis1.get(i).getNomProduit());
                   Label l8 =new Label(lis1.get(i).getAdresse());
                ///////////////////
                System.out.println(lis1.get(i).getNom());
                
                  btnmap = new Button("map ");
                        btnmap.addActionListener((ActionListener) (ActionEvent eve) -> {
                            GoogleMapartissant  hi;
                            try {
                                hi = new GoogleMapartissant();
                                hi.getF().show();
                            } catch (IOException ex) {
                                
                            }
               });
                         // f.add(   btnmap);
//**************************************************
               btnsuup = new Button("supprimer ");
                    btnsuup.addActionListener((ActionListener) (ActionEvent eve) -> {
                        CRUDPrLiv rs =new  CRUDPrLiv();
                        rs.suup(res ,z);
                        livraisonartisant a=new livraisonartisant();
                        a.getF().show();
               });
               
                  
                    
                    //********************************************************
        
      
     
        TextField x = new TextField("", "recherche");
         x.getText();
               con.add(l);
               con.add(l4);
               con.add(l7);
               con.add(l8);
              con.add( btnsuup);
                con.add(   btnmap);
              // con.add( btnmap);
              // con.add(somm);

             // f.add(x);
                    x.addActionListener((ActionListener) (ActionEvent eve) -> {
                        CRUDPrLiv rs =new  CRUDPrLiv();
                        //  rs.Recherche(, res);
                        capturephoto hello=new capturephoto();
                        //     new  capture().show();
                        hello.getF().show();
                        // Affichagecmd a=new Affichagecmd();
                        //a.getF().show();
               });
               conk.add(con);
        }
           f.add(conk);
     
            confirmer = new Button("confirmer"); 
               f.add(  confirmer);
          // pdf= new Button("pdf"); 
            //  f.add(  pdf);
              //***********************************************************
              capture = new Button("capture"); 
     
              capture.addActionListener((ActionListener) (ActionEvent eve) -> {
                  //  CRUDPrLiv rs =new  CRUDPrLiv();
                  //rs.supprimer(res ,z);
                  capturephoto hello=new capturephoto();
                  //     new  capture().show();
                  hello.getF().show();
                  // Affichagecmd a=new Affichagecmd();
                  //a.getF().show();
        });
               f.add(  capture);
         /*   //           btnmap = new Button("map ");
                        btnmap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eve) {
              GoogleMap hi=new GoogleMap();
               hi.getF().show();
                   // Affichagecmd a=new Affichagecmd();
               //a.getF().show();
                       }
        });
                         f.add(   btnmap);*/
        //***************************************************************
           
        
          f.getToolbar().addCommandToOverflowMenu("Back", null, (ev)->{
             new Affichage(res).show();
          });
          confirmer.addActionListener((e)->{
         
              
              
 AuthMethod auth = new TokenAuthMethod("279140e7", "vhoE08C3jBDopatz");  // (api_key,api_secret)
                        NexmoClient client = new NexmoClient(auth);
                        SmsSubmissionResult[] responses;
                        
                     
            try {
                responses = client.getSmsClient().submitMessage(new TextMessage(
                        "meryem",
                        "+21692569606",
                        "vous  commande sera delivree dans deux jour merci pour votre confiance" ));
                
                            for (SmsSubmissionResult response : responses) {
                                System.out.println(response);
                            }
            } catch (IOException | NexmoClientException ex) {
          
            }
              
              
        });
      
    }
    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}

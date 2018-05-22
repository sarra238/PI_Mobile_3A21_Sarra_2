/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiProduitLivraison;

import Entities.Produit;
import Services.CRUDPrLiv;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;
import com.codename1.ui.util.Resources;
import java.io.IOException;


/**
 *
 * @author haythem
 */
public class Affichagecmd {
    
    Form f;
    SpanLabel lb;
     Button btnsup,confcommande, btnmap, pdf,capture;
  Resources res;
  float somme;
  EncodedImage enc;
    Image img;
    ImageViewer imgv;
    public String url= "http://localhost/SoukI/web/images3/";
           Label somm,mer;
    public Affichagecmd() {
        res = UIManager.initFirstTheme("/theme_1");
        f = new Form();
        CRUDPrLiv c =new CRUDPrLiv();
        Container conk = new Container(BoxLayout.y());
        ArrayList<Produit> lis=c.getallcmd();
           for (int i = 0; i < lis.size(); i++) {
    final Produit prod=lis.get(i);
  
               Container con = new Container(BoxLayout.y());
                try {
                enc = EncodedImage.create("/giphy.gif").scaledEncoded(150,150);
            }
            catch (IOException ex) {
                System.out.println("error encoder");
            }
            String urll=url+lis.get(i).getNomImage();
            img = URLImage.createToStorage(enc, urll+"j", urll, URLImage.RESIZE_SCALE);
            imgv = new ImageViewer();
            imgv.setImage(img);
               lb=new SpanLabel("");
                 int z =lis.get(i).getId();
                 somme=(float) (somme+lis.get(i).getPrix());
               lb.setText(lis.get(i).getNomProduit());
               Container cc= new Container(BoxLayout.x());
               Label Region=new Label(lis.get(i).getRegion());
               Label Prix =new Label(Double.toString(lis.get(i).getPrix())+" dt");
               cc.add(Region);
               cc.add(Prix);
               SpanLabel ll=new SpanLabel(lis.get(i).getDescription());
               Label lll=new Label("******************************************");
               
                  btnmap = new Button("map ");
                 btnmap.addActionListener((ActionListener) (ActionEvent eve) -> {
                     try {
                         GoogleMap hi;
                         hi = new GoogleMap(prod.getAttitude(),prod.getLongitude());
                         hi.getF().show();
                     } catch (IOException ex) {   }
    });
                        
//**************************************************
               btnsup = new Button("supprimer ");
                    btnsup.addActionListener((ActionListener) (ActionEvent eve) -> {
                        CRUDPrLiv rs =new  CRUDPrLiv();
                        rs.supprimer(res ,z);
                        Affichagecmd a=new Affichagecmd();
                        a.getF().show();
    });
               
                    
                    //********************************************************
        
       /* Label somm = new Label("le prix totales: ");
             String s=Float.toString(somme);
         Label mer = new Label(s);*/
     //***********************************************************
        TextField x = new TextField("", "recherche");
         x.getText();
         //**********************************
         con.add(imgv);      
         con.add(lb);
         con.add(cc);
         con.add(ll);
        Container ck= new Container(BoxLayout.x());
               ck.add( btnsup);
                ck.add( btnmap);
                
               con.add(ck);
               con.add(lll);
               conk.add(con);
                //f.add(   btnmap);
        
                  
         
                    x.addActionListener((ActionListener) (ActionEvent eve) -> {
                        CRUDPrLiv rs =new  CRUDPrLiv();
                        //  rs.Recherche(, res);
                        capturephoto hello=new capturephoto();
                        //     new  capture().show();
                        hello.getF().show();
                        // Affichagecmd a=new Affichagecmd();
                        //a.getF().show();
    });
               
        }f.add(conk);
               Label somm = new Label("le prix totales: ");
         String s=Float.toString(somme);
         Label mer = new Label(s);
                f.add( somm);
                f.add( mer);
           
       // ImageViewer img;
       // img = new ImageViewer("panier.jpg");
            confcommande = new Button("confirmer"); 
               f.add(  confcommande);
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
           
        
          f.getToolbar().addCommandToLeftBar("Back", null, (ev)->{
              new Affichage(res).show();
          });
          confcommande.addActionListener((e)->{
            Ajouter a=new Ajouter();
        // a.getF().show();
        });
      
    }
    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}

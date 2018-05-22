/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produit;
import Entities.livraison;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author haythem
 */
public class CRUDPrLiv {
    
        public ArrayList<Produit> getallrdv() {
        ArrayList<Produit> listrdv = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/Produit/Affmobile");
        con.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp = new JSONParser();
            
            try {
                Map<String, Object> rdvs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(rdvs);
                List<Map<String, Object>> list = (List<Map<String, Object>>) rdvs.get("listRendezVous");
                for (Map<String, Object> obj : list) {
                    Produit r = new Produit();
                    
                    float id = Float.parseFloat(obj.get("id").toString());
                    r.setId((int) id);
                    r.setNomProduit(obj.get("NomProduit").toString());
                    r.setRegion(obj.get("Region").toString());
                    float prix = Float.parseFloat(obj.get("prix").toString());
                    r.setPrix((int) prix);
                    r.setDescription(obj.get("Description").toString());
                    r.setNomImage(obj.get("nomImage").toString());
                    listrdv.add(r);
                    
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listrdv;
    }
        //******************
       
        
        //***********************
        
        ///**************************************
             public ArrayList<Produit> getallcmd() {
        ArrayList<Produit> listcmd = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/Produit/Affcommandemobile");
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                Map<String, Object> rdvs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(rdvs);
                //System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) rdvs.get("listRendez");
                for (Map<String, Object> obj : list) {
                    Produit r = new Produit();
                    
                    float id = Float.parseFloat(obj.get("id").toString());
                    r.setId((int) id);
                    
                    r.setNomProduit(obj.get("NomProduit").toString());
                    r.setRegion(obj.get("Region").toString());
                    float prix = Float.parseFloat(obj.get("prix").toString());
                    r.setPrix((int) prix);
                    r.setDescription(obj.get("Description").toString());
                    float longitude = Float.parseFloat(obj.get("longitude").toString());
                    r.setLongitude((int) longitude);
                    float attitude = Float.parseFloat(obj.get("attitude").toString());
                    r.setAttitude((int) attitude);
                    r.setNomImage(obj.get("nomImage").toString());
                    
                    
                    listcmd.add(r);
                    
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcmd;
    }
        //******************
       public void ajouter(int id) {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/SoukI/web/app_dev.php/Produit/valider12/"+ id + "");
        NetworkManager.getInstance().addToQueue(req);
       
       // refreshTheme();
        
        //new ClubInterface(res).show();

    }
        //****************
     
           
        public void supprimer(Resources res, int id) {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/SoukI/web/app_dev.php/Produit/supprimer12/"+ id +"");
        NetworkManager.getInstance().addToQueue(req);
        } 
           
       //*********************************** 
     
//    public String ret() {
//        TextField x = new TextField("", "recherche");
//        return x.getText();
//    }

  /*  public void Recherche(String x, Resources res) {
        ConnectionRequest req = new ConnectionRequest();

        req.setUrl("http://localhost/HighShop_Web/web/app_dev.php/find/?nomProduit=" + x
                + "");

        // new ClubRechercher(res, x).show();
    }
        //************************************
           
           
      */     
           
   //*****************************************
      
//*************************************************  
        
           public ArrayList<livraison> getallliv() {
        ArrayList<livraison> listliv = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/Produit/livraison");
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                Map<String, Object> rdvs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(rdvs);
                //System.out.println(tasks);
                List<Map<String, Object>> list1 = (List<Map<String, Object>>) rdvs.get("listRendezVous");
                for (Map<String, Object> obj : list1) {
                    livraison r = new livraison();
                    float id = Float.parseFloat(obj.get("id").toString());
                    r.setId((int) id);
                    r.setNom(obj.get("Nom").toString());
                    r.setPrenom(obj.get("prenom").toString());
                    r.setAdresse(obj.get("adresse").toString());
                    r.setNomProduit(obj.get("nomProduit").toString());
                    float quantite = Float.parseFloat(obj.get("quantite").toString());
                    r.setQuantite((int) quantite);
                    
                    System.err.println(quantite);
                    listliv.add(r);
                    System.err.println(listliv);
                    
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listliv;
              
    }
        
        
        //********************************************
             public void suup(Resources res, int id) {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/SoukI/web/app_dev.php/Produit/suup/"+ id +"");
        NetworkManager.getInstance().addToQueue(req);
        } 
    //******************************
}

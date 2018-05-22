/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.User;
import Entities.particEv;
import Entities.reservationEvent;
import static Services.UserServices.conn;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lv
 */
public class reserEvService {
    
     public void ajoutPar (int t){
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/event/addRes/"+t+"/" + conn;
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      public void conf (int t){
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/event/conf/"+t;
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
       public void supp (int t ){
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/event/suppRes/"+t ;
        con.setUrl(Url);
        

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
       
         public void suppM (int t ){
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/event/suppResM/"+conn+"/"+t ;
        con.setUrl(Url);
        

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
       
       
     public ArrayList<reservationEvent> getListRese(String json) {

 
    

        ArrayList<reservationEvent> listEvent = new ArrayList<>();

        try {
         //   System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
       //     System.out.println(event);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
         //   System.out.println(list);

            for (Map<String, Object> obj : list) {
                reservationEvent e = new reservationEvent();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                //System.out.println(id);
                e.setId((int) id);
          ///e.setUserId(O(obj.get("id").toString().trim()));
                e.setNom(obj.get("nom").toString());
               e.setPrenom(obj.get("prenom").toString());
               e.setMail(obj.get("mail").toString());
         
             //   Date deb (obj.get("dateDeb").toString());
             // e.setDateDeb(obj.get("dateDeb").toString());
                 // e.setDateFin(obj.get("dateFin").toString());
                        
                   
              // int idr= n.getId();
              
            //  e.setUserId(idr);
              
            
             //   System.out.println(obj.get("idUser").toString());
                         
                         
                               //   System.out.println(les2.get(i1).getType());
                 
                                 listEvent.add(e);  
                              
            
                                  
                          
               // System.out.println(e);
               

            }}

         catch (IOException ex) {
        }
        System.out.println(listEvent);
        return listEvent;

    }
     
    ArrayList<reservationEvent> listEvent = new ArrayList<>();
    
    public ArrayList<reservationEvent> getList2(int t){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/event/affichResO/"+t);  
        con.addResponseListener((NetworkEvent evt) -> {
            reserEvService ser = new reserEvService();
            listEvent = ser.getListRese(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvent;
    }
  
     public String  getU(String json) {
      //  ArrayList<User> user = new ArrayList<>();

      
    String p="";
     
        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(event);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            System.out.println(list);

            for (Map<String, Object> obj : list) {
               

                
          ///e.setUserId(O(obj.get("id").toString().trim()));
             
         p=obj.get("root").toString();     
                      }

        } catch (IOException ex) {
        }
       
        return p;

    }
     
     
    String user2 ;
    
    public String getUser(int t){  
     
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/event/getu/"+106);  
        con.addResponseListener((NetworkEvent evt) -> {
            reserEvService ser = new reserEvService();
            user2 = ser.getU(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return user2;
    }
    
    /***********/
    
    /*  String l="";
    public String getUsr(int t){
    ArrayList<String> listpediatres = new ArrayList<>();
    ConnectionRequest con = new ConnectionRequest();
    
    con.setUrl("http://localhost/SoukI/web/app_dev.php/event/getu/"+106);
    
    con.addResponseListener((NetworkEvent evt) -> {
    //listTasks = getListTask(new String(con.getResponseData()));
    JSONParser jsonp = new JSONParser();
    
    try {
    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
    
    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
    list.stream().map((result) -> {
    l=(result.get("root").toString());
    return result;
    }).forEachOrdered((_item) -> {
    listpediatres.add(l);
    });
    } catch (IOException ex) {
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(con);
    // NetworkManager.getInstance().addToQueue(con); ==> ye5dem toul affichage mayestanech resultat tjih heka 3leh tji ferr8a
    System.out.println(listpediatres.get(0));
    return listpediatres.get(0);
    
    }
    */
}
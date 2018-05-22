/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import Entities.Evenement;
import Entities.particEv;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lv
 */
public class EvenementServices {
     public ArrayList<Evenement> getListEvent(String json) {

        ArrayList<Evenement> listEvent = new ArrayList<>();

        try {
         //   System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
       //     System.out.println(event);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
         //   System.out.println(list);

            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                //System.out.println(id);
                e.setId((int) id);
          ///e.setUserId(O(obj.get("id").toString().trim()));
                e.setNomEvenement(obj.get("nomEvenement").toString());
               e.setDescription(obj.get("description").toString());
               e.setNomImg(obj.get("nomImg").toString());
           e.setDateDeb(obj.get("dateDeb2").toString());
                //System.out.println(obj.get("dateDeb2").toString());
           e.setDateFin(obj.get("dateFin2").toString());
          
             //   Date deb (obj.get("dateDeb").toString());
             // e.setDateDeb(obj.get("dateDeb").toString());
                 // e.setDateFin(obj.get("dateFin").toString());
                         e.setType(obj.get("type").toString());
                   ///      System.out.println(obj.get("latitude").toString());
                        float lat =Float.parseFloat(obj.get("latitude").toString());
                         e.setLat((double)lat);
                         float lang = Float.parseFloat(obj.get("longitude").toString());
                         e.setLang((double)lang);
              
                   
              // int idr= n.getId();
              
            //  e.setUserId(idr);
              
            
             //   System.out.println(obj.get("idUser").toString());
                          e.setLocalisation(obj.get("localisation").toString());
                          PartService p2 = new PartService();
                          ArrayList<particEv> les2=p2.affichpar(e.getId());
                       //   System.out.println(e.getId()+e.getType());
                          if(les2.size()==1){
                               for (int i1 = 0; i1 < 1; i1++) {
                              
                              if ((les2.get(i1).getType().equals("nest pas interesé(e)"))== true ) {
                                  
                                  
                               //   System.out.println(les2.get(i1).getType());
                 listEvent.remove(e);
                          }
                                else {
                                 listEvent.add(e);  
                              }}
                          }
                              else {
                                 listEvent.add(e);  
                              }
            
                                  
                          
               // System.out.println(e);
               

            }}

         catch (IOException ex) {
        }
        System.out.println(listEvent);
        return listEvent;

    }
     
    ArrayList<Evenement> listEvent = new ArrayList<>();
    
    public ArrayList<Evenement> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/event/a");  
        con.addResponseListener((NetworkEvent evt) -> {
            EvenementServices ser = new EvenementServices();
            listEvent = ser.getListEvent(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvent;
    }
    ArrayList<Evenement> listEvent2 = new ArrayList<>();
    
    public ArrayList<Evenement> getList3(int t){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/event/b/"+t);  
        con.addResponseListener((NetworkEvent evt) -> {
            EvenementServices ser = new EvenementServices();
            listEvent2 = ser.getListEvent(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvent2;
    }
         public void supp (int t ){
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/event/suppE/"+t ;
        con.setUrl(Url);
        

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
       
}

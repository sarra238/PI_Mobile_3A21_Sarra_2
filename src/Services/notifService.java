/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.notif;
import Entities.particEv;
import static Services.UserServices.conn;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lv
 */
public class notifService {
      public void ajoutNot (String ide){
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/event/notif/"+conn+"/vous etes inscrit a l'evenement "+ide;
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
         //   System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      public void ajoutNot2 (String ide,int id){
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/event/notif/"+id+"/"+ide;
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
         //   System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
         public ArrayList<notif> getListEvent(String json) {

        ArrayList<notif> listEvent = new ArrayList<>();

        try {
         //   System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
       //     System.out.println(event);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
         //   System.out.println(list);

            for (Map<String, Object> obj : list) {
                notif e = new notif();

                // System.out.println(obj.get("id"));
           
                //System.out.println(obj.get("dateDeb2").toString());
           e.setText(obj.get("text").toString());
          
             
                                 listEvent.add(e);  
                             
            
            }}

         catch (IOException ex) {
        }
        System.out.println(listEvent);
        return listEvent;

    }
     
    ArrayList<notif> listEvent = new ArrayList<>();
    
    public ArrayList<notif> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/event/affnot/"+conn);  
        con.addResponseListener((NetworkEvent evt) -> {
           notifService ser = new notifService();
            listEvent = ser.getListEvent(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvent;
    }
    ArrayList<notif> listEvent2 = new ArrayList<>();
    
}

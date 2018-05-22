/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.commentaire;
import Entities.particEv;
import static Services.UserServices.conn;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lv
 */
public class commService {
     public void ajoutCom (commentaire t){
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/event/addCmo/"+t.getIdEv()+"/"+conn+"/" + t.getComm();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void modifCom (commentaire t){
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/event/serP/"+t.getIdEv()+"/"+conn+"/" + t.getComm();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public ArrayList<commentaire> getListC(String json) {

        ArrayList<commentaire> listCommentaire = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(event);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            System.out.println(list);

            for (Map<String, Object> obj : list) {
                commentaire e = new commentaire();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                //System.out.println(id);
                e.setId((int) id);
          ///e.setUserId(O(obj.get("id").toString().trim()));
                e.setComm(obj.get("commentaire").toString());
    //          e.setDate((Date) obj.get("date"));
          
                        
      
                listCommentaire.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listCommentaire);
        return listCommentaire;

    }
     
    ArrayList<commentaire> listCommentaire = new ArrayList<>();
    
    public ArrayList<commentaire> affichcom(Evenement t){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/event/afficheCom/"+t.getId());  
        con.addResponseListener((NetworkEvent evt) -> {
            commService ser = new commService();
            listCommentaire = ser.getListC(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCommentaire;
    }
    
}

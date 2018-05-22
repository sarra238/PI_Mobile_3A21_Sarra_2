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
import Entities.particEv;
import static Services.UserServices.conn;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lv
 */
public class PartService {
    public void ajoutPar (particEv t){
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/event/newP/"+conn+"/"+t.getIdEv()+"/" + t.getType();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void modifPar (particEv t){
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/event/serP/"+conn+"/"+t.getIdEv()+"/" + t.getType();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
          public ArrayList<particEv> getListP(String json) {

        ArrayList<particEv> listEvent = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(event);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            System.out.println(list);

            for (Map<String, Object> obj : list) {
                particEv e = new particEv();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                //System.out.println(id);
                e.setId((int) id);
          ///e.setUserId(O(obj.get("id").toString().trim()));
                e.setType(obj.get("type").toString());
              
          
                        
      
                listEvent.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEvent);
        return listEvent;

    }
     public int getListP2(String json) {

      int l=0 ;

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(event);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            System.out.println(list);

            for (Map<String, Object> obj : list) {
               
 float id = Float.parseFloat(obj.get("nbre").toString());
                // System.out.println(obj.get("id"));
                
          ///e.setUserId(O(obj.get("id").toString().trim()));
             
         l=(int)id;     
          
              }

        } catch (IOException ex) {
        }
       
        return l;

    }
      public int getListP3(String json) {

      int l=0 ;

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(event);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            System.out.println(list);

            for (Map<String, Object> obj : list) {
               
 float id = Float.parseFloat(obj.get("nbre2").toString());
                // System.out.println(obj.get("id"));
                
          ///e.setUserId(O(obj.get("id").toString().trim()));
             
         l=(int)id;     
                      }

        } catch (IOException ex) {
        }
       
        return l;

    }
         public int getListP4(String json) {

      int l=0 ;

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(event);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            System.out.println(list);

            for (Map<String, Object> obj : list) {
               
 float id = Float.parseFloat(obj.get("nbre2").toString());
                // System.out.println(obj.get("id"));
                
          ///e.setUserId(O(obj.get("id").toString().trim()));
             
         l=(int)id;     
                      }

        } catch (IOException ex) {
        }
       
        return l;

    }
     
    ArrayList<particEv> listEvent = new ArrayList<>();
       ArrayList<particEv> listEvent2 = new ArrayList<>();
    public ArrayList<particEv> affichpar(int t){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/event/serPm/"+conn+"/"+t);  
        con.addResponseListener((NetworkEvent evt) -> {
            PartService ser = new PartService();
            listEvent = ser.getListP(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvent;
    }

 int l=0;
    public int affichparEv(int t){   
       ArrayList<Integer> listpediatres = new ArrayList<>();        
        ConnectionRequest con = new ConnectionRequest();
      
     con.setUrl("http://localhost/SoukI/web/app_dev.php/event/countInter/"+t);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> result : list) {
                

                        float id = Float.parseFloat(result.get("nbre").toString());
                       

                        listpediatres.add((int) id);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        // NetworkManager.getInstance().addToQueue(con); ==> ye5dem toul affichage mayestanech resultat tjih heka 3leh tji ferr8a 
System.out.println(listpediatres.get(0));
        return listpediatres.get(0);

    }
  
    public int affichnnEv(int t){   
                   ArrayList<Integer> listpediatres = new ArrayList<>();        
        ConnectionRequest con = new ConnectionRequest();
      
     con.setUrl("http://localhost/SoukI/web/app_dev.php/event/countnnInt/"+t);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> result : list) {
                

                        float id = Float.parseFloat(result.get("nbre2").toString());
                       

                        listpediatres.add((int) id);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        // NetworkManager.getInstance().addToQueue(con); ==> ye5dem toul affichage mayestanech resultat tjih heka 3leh tji ferr8a 
System.out.println(listpediatres.get(0));
        return listpediatres.get(0);

        
    }
     public int Participant(int t){   
                        ArrayList<Integer> listpediatres = new ArrayList<>();        
        ConnectionRequest con = new ConnectionRequest();
      
     con.setUrl("http://localhost/SoukI/web/app_dev.php/event/Participant/"+t);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> result : list) {
                

                        float id = Float.parseFloat(result.get("nbre2").toString());
                       

                        listpediatres.add((int) id);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        // NetworkManager.getInstance().addToQueue(con); ==> ye5dem toul affichage mayestanech resultat tjih heka 3leh tji ferr8a 
System.out.println(listpediatres.get(0));
        return listpediatres.get(0);     
        
    }
    String type;
     public String Typepar(int t){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/event/type/"+t+"/"+conn);  
        con.addResponseListener((NetworkEvent evt) -> {
            PartService ser = new PartService();
       type = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return type;
    }
       ArrayList<particEv> listttEvent = new ArrayList<>();
    
    public ArrayList<particEv> affichttpar(int t){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/event/afftt/"+t);  
        con.addResponseListener((NetworkEvent evt) -> {
            PartService ser = new PartService();
            listttEvent = ser.getListP(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listttEvent;
    }
}

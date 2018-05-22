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
import com.codename1.ui.events.ActionListener;
import Entities.Rating;
import Entities.Restaurant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ServiceRestaurant {

    public void ajoutResto(Restaurant ta) {
        /*
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://41.226.11.243:10004/tasks/" + ta.getNom() + "/" + ta.getAdresse();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    */}
    

    public ArrayList<Restaurant> getList() {
        ArrayList<Restaurant> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        //con.setUrl("http://41.226.11.243:10004/tasks/");
        con.setUrl("http://localhost/SoukI/web/app_dev.php/Resto/affichageMobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> restos = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("restoS"+restos);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) restos.get("root");
                    for (Map<String, Object> obj : list) {
                        Restaurant resto = new Restaurant();
                        // il a recuperer l entier comme etant float 
                        float id = Float.parseFloat(obj.get("id").toString());
                        float num = Float.parseFloat(obj.get("numtel").toString());
                        float latitude = Float.parseFloat(obj.get("latitute").toString());
                        float longtitude = Float.parseFloat(obj.get("longitude").toString());
                        //float user = Float.parseFloat(obj.get("idUser").toString());
                        
                        //resto.setId((int)obj.get("id"));
                        resto.setId((int) id);
                        resto.setNom(obj.get("nom").toString());
                        resto.setAdresse(obj.get("adresse").toString());
                        resto.setNumtel((int) num);
                        resto.setCategorie(obj.get("categorie").toString());
                        resto.setValide(obj.get("valide").toString());
                        resto.setLaitute(latitude);
                        resto.setLongtitude( longtitude );
                        //resto.setIdUser((int) user);
                        listTasks.add(resto);

                    }
                } catch (IOException ex) {
                }

            }
        });
        // en attendant la reponse .. syncrone, en attendant la reponse pour afficher 
        //l affichage yestanna reponse lin tjih maghir affichage mayaffichi chay :p
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    
    
    public void rateee(Rating ta) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/Resto/ratingMobile/"+ ta.getIdRestaurant()+"?rate="+ ta.getNote();
        con.setUrl(Url);
        
        System.out.println(con.getUrl());
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

}

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
import Entities.Reservation;
import Entities.Restaurant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author sana
 */
public class ServiceReservation {

    public void reserver(Reservation ta) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/Resto/reservationMobile/"+ ta.getId_user() +"/"+ ta.getId_Resto() +"?date="+ta.getDate()+"&nbr=" + ta.getNbr();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    

    public ArrayList<Reservation> getListReservation(int idu) {
        ArrayList<Reservation> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        //con.setUrl("http://41.226.11.243:10004/tasks/");
        con.setUrl("http://localhost/SoukI/web/app_dev.php/Resto/affichageMobileClient/"+idu);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> restos = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println("restoS"+restos);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) restos.get("root");
                    for (Map<String, Object> obj : list) {
                        Reservation resto = new Reservation();
                        // il a recuperer l entier comme etant float 
                        float id = Float.parseFloat(obj.get("id").toString());
                        float n = Float.parseFloat(obj.get("NbrePersonne").toString());
                        float idRest = Float.parseFloat(obj.get("id_Resto").toString());
                                                
                        
                        resto.setId((int) id);
                        //resto.setDate(sdfr.format(obj.get("dateReservation")));
                        //resto.setDate(obj.get("dateReservation").toString());
                        resto.setDate(obj.get("dateReservation").toString());
                        resto.setNbr((int) n);
                        resto.setId_Resto((int) idRest);
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

    public String getNomReservation(int idRest) {
        Restaurant resto = new Restaurant();
        //ArrayList<Restaurant> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        //con.setUrl("http://41.226.11.243:10004/tasks/");
        con.setUrl("http://localhost/SoukI/web/app_dev.php/Resto/getNomRestaurant/"+idRest);
        System.out.println("URL"+con.getUrl());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> restos = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println("reservation"+restos);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) restos.get("root");
                    //System.out.println("list Test "+ list.toString());
                    for (Map<String, Object> obj : list) {

                        float num = Float.parseFloat(obj.get("numtel").toString());
                        float latitude = Float.parseFloat(obj.get("latitute").toString());
                        float longtitude = Float.parseFloat(obj.get("longitude").toString());
                        //float user = Float.parseFloat(obj.get("idUser").toString());
                        
                        //resto.setId((int)obj.get("id"));
                        resto.setNom(obj.get("nom").toString());
                        resto.setAdresse(obj.get("adresse").toString());
                        resto.setNumtel((int) num);
                        resto.setCategorie(obj.get("categorie").toString());
                        resto.setValide(obj.get("valide").toString());
                        resto.setLaitute(latitude);
                        resto.setLongtitude( longtitude );
                        //resto.setIdUser((int) user);
                        
                        System.out.println("num"+num);
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        // en attendant la reponse .. syncrone, en attendant la reponse pour afficher 
        //l affichage yestanna reponse lin tjih maghir affichage mayaffichi chay :p
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resto.getNom();
    }
    
    ////////
    
    
    public String getCategorieReservation(int idRest) {
        Restaurant resto = new Restaurant();
        //ArrayList<Restaurant> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        //con.setUrl("http://41.226.11.243:10004/tasks/");
        con.setUrl("http://localhost/SoukI/web/app_dev.php/Resto/getNomRestaurant/"+idRest);
        System.out.println("URL"+con.getUrl());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> restos = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println("reservation"+restos);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) restos.get("root");
                    //System.out.println("list Test "+ list.toString());
                    for (Map<String, Object> obj : list) {
                    
                        
                        float num = Float.parseFloat(obj.get("numtel").toString());
                        float latitude = Float.parseFloat(obj.get("latitute").toString());
                        float longtitude = Float.parseFloat(obj.get("longitude").toString());
                        //float user = Float.parseFloat(obj.get("idUser").toString());
                        
                        //resto.setId((int)obj.get("id"));
                        resto.setNom(obj.get("nom").toString());
                        resto.setAdresse(obj.get("adresse").toString());
                        resto.setNumtel((int) num);
                        resto.setCategorie(obj.get("categorie").toString());
                        resto.setValide(obj.get("valide").toString());
                        resto.setLaitute(latitude);
                        resto.setLongtitude( longtitude );
                        //resto.setIdUser((int) user);
                        
                        System.out.println("num"+num);
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        // en attendant la reponse .. syncrone, en attendant la reponse pour afficher 
        //l affichage yestanna reponse lin tjih maghir affichage mayaffichi chay :p
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resto.getCategorie();
    }
    
    public String getTelReservation(int idRest) {
        Restaurant resto = new Restaurant();
        //ArrayList<Restaurant> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        //con.setUrl("http://41.226.11.243:10004/tasks/");
        con.setUrl("http://localhost/SoukI/web/app_dev.php/Resto/getNomRestaurant/"+idRest);
        System.out.println("URL"+con.getUrl());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> restos = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println("reservation"+restos);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) restos.get("root");
                    //System.out.println("list Test "+ list.toString());
                    for (Map<String, Object> obj : list) {

                        float num = Float.parseFloat(obj.get("numtel").toString());
                        float latitude = Float.parseFloat(obj.get("latitute").toString());
                        float longtitude = Float.parseFloat(obj.get("longitude").toString());
                        //float user = Float.parseFloat(obj.get("idUser").toString());
                        
                        //resto.setId((int)obj.get("id"));
                        resto.setNom(obj.get("nom").toString());
                        resto.setAdresse(obj.get("adresse").toString());
                        resto.setNumtel((int) num);
                        resto.setCategorie(obj.get("categorie").toString());
                        resto.setValide(obj.get("valide").toString());
                        resto.setLaitute(latitude);
                        resto.setLongtitude( longtitude );
                        //resto.setIdUser((int) user);
                        
                        System.out.println("num"+num);
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        // en attendant la reponse .. syncrone, en attendant la reponse pour afficher 
        //l affichage yestanna reponse lin tjih maghir affichage mayaffichi chay :p
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ""+resto.getNumtel();
    }
    
    public void AnnulerReservation(Reservation ta) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/Resto/annulerReservation/"+ta.getId();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
        String str = new String(con.getResponseData());
        System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void updateReservation(Reservation ta) {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/Resto/updateReservationMobile/"+ ta.getId() +"/"+ ta.getDate() +"/"+ta.getNbr();
        //String Url = "http://localhost/SoukI/web/app_dev.php/Resto/updateReservationMobile/"+ ta.getId() +"/2018-02-02/98";
        con.setUrl(Url);
        System.out.println(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
}




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiRestaurant;

import com.codename1.components.SpanLabel;
import com.codename1.db.Database;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import Services.ServiceRestaurant;
import Entities.Restaurant;
import java.util.ArrayList;
import com.codename1.ui.util.Resources;
import Services.ServiceReservation;
import Entities.Rating;
import Entities.Reservation;
import java.util.Date;



public class Affichage {

    //SQL Lite
    Database db;    
    boolean created=false;
    private Form current;
    private Resources theme;
    
    Rate rat = new Rate();
    
    
    Form f;
    Form f2,f3;
    SpanLabel lb;
    String dateD;
        
    public Affichage() {
        /*
        //SQL Lite
        created = Database.exists("hamouda");
        try {
            db = Database.openOrCreate("hamouda");
            
            if (created == false )
            {

         db.execute("CREATE TABLE favoris ( Animal TEXT , Description TEXT ,  Prix Float  );");
       
    
            }
        }
        catch (IOException ex)
        {
        }
        /// end SQL lite
        */
        f = new Form("Restaurant ",BoxLayout.y());
        
        lb = new SpanLabel("");
        f.add(lb);
        
        
        
        ServiceRestaurant serviceResto = new ServiceRestaurant();
        ArrayList<Restaurant> list = serviceResto.getList();
        
        
        ServiceReservation reserv = new ServiceReservation();
        
        
        ///////
        
        for (Restaurant t : list ){
            Container c = new Container();
            Label l = new Label(t.getNom());
            c.add(l);
            
            f.add(c);
            
            Button b = new Button();
            f2 = new Form("Restaurant ",BoxLayout.y());
            f3 = new Form("Restaurant ",BoxLayout.y());
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    f2.removeAll();
                    
                    /*
                        Font fnt = body.getUnselectedStyle().getFont();
                        body.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(n.getFontSize()), Font.STYLE_PLAIN));
                    */
                    
                    /*SliderBridge rate =new SliderBridge();
                     
                    rate.setEnabled(true);
                    rate.setEditable(true);
                    rate.setMaxValue(5);
                    rate.getProgress();
                      */ 
                    /**/
                    
                    Rate rat = new Rate();
    
                    
                    rat.getF().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
////fi wost il action 7otil service mté3ik illi béch yajoutilik rating fil base éna consomitou mil web
                                
                                Rating rating = new Rating(); 
                                rating.setIdRestaurant(t.getId());
                                rating.setNote(rat.getF().getProgress());
                                ServiceRestaurant service = new ServiceRestaurant();
                                service.rateee(rating);
                                rat.getF().setVisible(false);
                                //labrate
                                System.out.println("/////" + rat.getF().getProgress());
                                //lbNoteRating.setVisible(true);

                            }
                        });
                    
                    /**/
                    
                    Container c2 = new Container(BoxLayout.y());
                    Container cRate = new Container();
                    Label nom = new Label(t.getNom());
                    SpanLabel adresse = new SpanLabel(t.getAdresse());
                    Label cat = new Label("Categorie ");
                    SpanLabel categorie = new SpanLabel(t.getCategorie());
                    Container ccat = new Container(BoxLayout.x());
                    ccat.add(cat);
                    ccat.add(categorie);
                    Label tel = new Label("N°        ");
                    SpanLabel num = new SpanLabel(""+t.getNumtel());
                    Container ctel = new Container(BoxLayout.x());
                    ctel.add(tel);
                    ctel.add(num);

                    Button btnReserver = new Button("Reserver");

                    btnReserver.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            f3.removeAll();
                            Container c3 = new Container(BoxLayout.y());
                            Container c4 = new Container(BoxLayout.x());
                            Label nbr = new Label("Reservation de");
                            ComboBox<Integer> combo = new ComboBox();
                            Label nbr1 = new Label(" personne(s)");
                            c4.add(nbr);
                            c4.add(combo);
                            c4.add(nbr1);
                            
                            combo.addItem(1);
                            combo.addItem(2);
                            combo.addItem(3);
                            combo.addItem(4);
                            combo.addItem(5);
                            combo.addItem(6);
                            combo.addItem(7);
                            combo.addItem(8);
                            combo.addItem(9);
                            combo.addItem(10);
                            
                            Label h = new Label("Reservation le ");
                            //TextField txt = new TextField(null, "Date resrvation");
                            
                            Calendar c = new Calendar();
                            c.setDate(new Date());
                            //Date d1 = new Date();
                            
                            Button btnValider = new Button("Valider");
                            
                            //
                            
                                btnValider.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                
                                    /*
                                    //SQL Lite
                                //  Annonce aa = new Annonce(a.getAnimal(),a.getDescription(),a.getPrix());
                                try { 

                                //User idcnx = Main.getUserCnx();
                                db.execute("INSERT INTO Favoris (  Animal, Description,Prix ) VALUES ('"+a.getAnimal()+"','"+a.getDescription()+"',"+a.getPrix()+");");
                                Dialog.show("Produit Ajouté aux favoris avec succes", "favori", "ok", null);
                                System.out.println("this is"+a.getAnimal()+""+a.getDescription());


                                System.out.println("ajout ok");

                                } catch (IOException ex) {
                                System.out.println("erreur d'insertion");
                                }
                                    
                                // end SQL Lite
                                */
                                    dateD = new SimpleDateFormat("yyyy-MM-dd").format(c.getDate());
                                    
                                    
                                    Reservation ta = new Reservation(); 
                                    int i = t.getId();
                                    
                                    ta.setId_Resto(i);
                                    ta.setId_user(13);
                                    ta.setDate(dateD);
                                    ta.setNbr(combo.getSelectedItem());
                                        
                                    f.show();
                                        
                                    
                                    reserv.reserver(ta);
                                    
                                }
                            });
                            
                            //
                            
                            c3.add(c4);
                            c3.add(h);
                            c3.add(c);
                            f3.add(c3);
                            f3.add(btnValider);
                            f3.show();
                        }
                    });

                    c2.add(nom);
                    c2.add(adresse);
                    //c2.add(categorie);
                    //c2.add(num);
                    c2.add(ccat);
                    c2.add(ctel);
                    cRate.add("        ");
                    cRate.add(rat.getF());
                    f2.add(c2);
                    f2.add(cRate);
                    f2.add(btnReserver);
                    f2.show();
                }
             
            });
            c.setLeadComponent(b);
        }
        
        f3.getToolbar().addCommandToLeftBar("Back", null ,new ActionListener()
            {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                f2.show();
            }}
        );
        
        f2.getToolbar().addCommandToLeftBar("Back", null ,new ActionListener()
            {
            @Override
            public void actionPerformed(ActionEvent evt) {
                f.show();
            }}
        );
                
        f.getToolbar().addCommandToOverflowMenu("Mes Reservations", null, (ev)->
            {
                AllReservation mesRes = new AllReservation();
                mesRes.getF().show();
            }
        );

        
        f.getToolbar().addCommandToLeftBar("Back", null, (ev)->
            {
                
            }
        );
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}

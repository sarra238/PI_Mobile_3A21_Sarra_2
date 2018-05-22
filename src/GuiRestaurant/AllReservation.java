/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiRestaurant;

import com.codename1.components.SpanLabel;
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
import java.util.ArrayList;
import Services.ServiceReservation;
import Entities.Reservation;
import java.util.Date;



public class AllReservation {

    Form f;
    Form f2,f3,f4;
    SpanLabel lb;
    String dateD;
        
    public AllReservation() {
        
        f = new Form("Reservation ",BoxLayout.y());
        
        lb = new SpanLabel("");
        f.add(lb);
        
        
        
        ServiceReservation reserv = new ServiceReservation();
        ArrayList<Reservation> list = reserv.getListReservation(13);
        
        
        
        ///////
        
        for (Reservation t : list ){
            Container c = new Container();
            System.out.println("id reser : "+t.getId_Resto());
            String lNom = reserv.getNomReservation(t.getId_Resto());
            String lCat = reserv.getCategorieReservation(t.getId_Resto());
            String lTel = reserv.getTelReservation(t.getId_Resto());
            Label l = new Label(lNom);
            c.add(l);
            f.add(c);
            Button b = new Button();
            f2 = new Form();
            b.addActionListener((ActionListener) (ActionEvent evt) -> {
                f2.removeAll();
                Container c2 = new Container(BoxLayout.y());
                Container v = new Container(BoxLayout.y());
                //Calendar cal =new Calendar();
                //cal.setDate(t.getDate());
                //Date d1 = new Date();
                Label desc = new Label("Votre reservation chez ");
                Label nomRest = new Label(lNom);
                Label cat = new Label("Categorie");
                Label categorieRest = new Label(lCat);
                Label tel = new Label("N° Tel");
                Label telRest = new Label(""+lTel);
                Label DLabel = new Label("Reservation le ");
                Label dateLabel = new Label(t.getDate());
                Container c3 = new Container(BoxLayout.x());
                Label PLabel = new Label("Reservation pour");
                Label pres = new Label(""+t.getNbr());
                Label P1Label = new Label("personne(s)");
                c3.add(PLabel);
                c3.add(pres);
                c3.add(P1Label);
                c2.add(DLabel);
                c2.add(dateLabel);
                c2.add(desc);
                c2.add(nomRest);
                c2.add(cat);
                c2.add(categorieRest);
                c2.add(tel);
                c2.add(telRest);
                c2.add(c3);
                Container c4 = new Container(BoxLayout.x());
                Label tab = new Label("         ");
                Button btnSupp = new Button("Annuler");
                Button btnModif=new Button("Modifier");
                btnSupp.addActionListener((e) -> {
                    
                    reserv.AnnulerReservation(t);
                    Affichage a=new Affichage();
                    a.getF().show();
                    
                    
                });
                f4 = new Form();
                btnModif.addActionListener((e) -> {
                    f4.removeAll();
                    Reservation r = new Reservation();
                    Container test = new Container(BoxLayout.x());
                    Label lNbr = new Label("Nombre personne ");
                    ComboBox<Integer> combo = new ComboBox();
                    test.add(lNbr);
                    test.add(combo);
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
                    //combo.setSelectedItem(t.getNbr());
                    Calendar c1 = new Calendar();
                    c1.setDate(new Date());
                    Button btnValider = new Button("Valider");
                    btnValider.addActionListener((ActionListener) (ActionEvent evt1) -> {
                        dateD = new SimpleDateFormat("yyyy-MM-dd").format(c1.getDate());
                        r.setId(t.getId());
                        r.setDate(dateD);
                        r.setNbr(combo.getSelectedItem());
                        reserv.updateReservation(r);
                        Affichage a=new Affichage();
                        a.getF().show();
                    });
                    v.add(test);
                    v.add(c1);
                    v.add(btnValider);
                    f4.add(v);
                    f4.show();
                });
                c4.add(tab);
                c4.add(btnSupp);
                c4.add(btnModif);
                f2.add(c2);
                f2.add(c4);
                f2.show();
            });
            
            c.setLeadComponent(b);

            f2.getToolbar().addCommandToLeftBar("Back", null, (ev)->
            {
                AllReservation h=new AllReservation();
                h.getF().show();
            }
        );
        }
        
        f.getToolbar().addCommandToLeftBar("Back", null, (ev)->
            {
                Affichage lstResto = new Affichage();
                lstResto.getF().show();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiReclamation;


import com.codename1.components.SpanLabel;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import com.codename1.ui.util.Resources;
import Services.ServiceReclamation;
import Entities.Reclamation;
import GuiRestaurant.Rate;



public class AffichageRec {

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
        
    public AffichageRec() {
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
        f = new Form("Reclamation ",BoxLayout.y());
        
        lb = new SpanLabel("");
        f.add(lb);
        
        
        ///// id User a changer 
        ServiceReclamation serRec = new ServiceReclamation();
        ArrayList<Reclamation> list = serRec.getListReclamation(13);
        
        
        
        ///////
        
        for (Reclamation t : list ){
            Container c = new Container();
            Label l = new Label(t.getSujet());
            c.add(l);
            
            f.add(c);
            
            Button b = new Button();
            f2 = new Form("Reclamation",BoxLayout.y());
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
                    
                    
                    
                    Container c2 = new Container(BoxLayout.y());
                    Label LSujet = new Label("Sujet ");
                    Label sujet = new Label(t.getSujet());
                    Label LMessage = new Label("Message");
                    Label message = new Label(t.getMsg());
                    
                    c2.add(LSujet);
                    c2.add(sujet);
                    c2.add(LMessage);
                    c2.add(message);
                    
                    f2.add(c2);
                    f2.show();
                }
             
            });
            c.setLeadComponent(b);
        }
        
        f2.getToolbar().addCommandToLeftBar("Back", null ,new ActionListener()
            {
            @Override
            public void actionPerformed(ActionEvent evt) {
                f.show();
            }}
        );
                
        f.getToolbar().addCommandToLeftBar("Back", null ,new ActionListener()
            {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AddReclamation add = new AddReclamation();
                add.getF().show();
            }}
        );
        
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}

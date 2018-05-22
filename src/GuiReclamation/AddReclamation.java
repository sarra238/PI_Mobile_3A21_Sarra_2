/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiReclamation;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import Services.ServiceReclamation;
import Entities.Reclamation;

/**
 *
 * @author BRAIKI Ahmad
 */
public class AddReclamation {
    
    Form f;
    Reclamation rec;
    
    public AddReclamation(){
    
    f = new Form("Reclamation");
    rec = new Reclamation();
    Container c = new Container(BoxLayout.y());
    
    ServiceReclamation reclaaa = new ServiceReclamation();
    
    Label lsujet = new Label("Sujet");
    TextField sjt = new TextField("", "sujet de reclamation");
    
    Label lmsg = new Label("Message");
    TextArea message = new TextArea();
    
    Button btnAjouter = new Button ("Reclamer");
    
        
    /// a changer
        
        
        
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                rec.setIdUser(13);
                rec.setSujet(sjt.getText());
                rec.setMsg(message.getText());
                
                reclaaa.reclamer(rec);
                
                AffichageRec all = new AffichageRec();
                all.getF().show();
            
            }
        });

        
        f.getToolbar().addCommandToLeftBar("Back", null, (ev)->
            {
                
            }
        );
        
        
        f.getToolbar().addCommandToOverflowMenu("Mes reclamations", null, (ev)->
            {
                AffichageRec listRec = new AffichageRec();
                listRec.getF().show();
                
            }
        );
        
        c.add(lsujet);
        c.add(sjt);
        c.add(lmsg);
        c.add(message);
        f.add(c);
        f.add(btnAjouter);
        f.show();
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiAnnonce;

import Entities.Annonce;
import Services.AnnoncesServices;
import static Services.UserServices.conn;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.ProfileForm;
import java.io.IOException;
import java.util.ArrayList;

public class AffichAnnAllClt {
    private Form f;
    TextField nom;
    SpanLabel l,l2,l3,lr,lb;
    Container c,c1,c2,c3;
    private Image img;
    private Resources theme;
    public  String msg;
    public String url= "http://localhost/SoukI/web/imagesAnnonce/";
    private EncodedImage enc ;
    private ImageViewer imgv;
   
    public AffichAnnAllClt() {
        theme = UIManager.initFirstTheme("/theme_1");
        f=new Form("Annonces!");
        c2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        AnnoncesServices s =new AnnoncesServices();
        ArrayList<Annonce> le = s.getList4(conn);
        Form f2=new Form("Détails!",new FlowLayout(Component.CENTER, Component.CENTER));
        for (int i = 0; i < le.size(); i++) {
            Toolbar tb1= f2.getToolbar();
            c=new Container(BoxLayout.x());
            try {
                enc = EncodedImage.create("/giphy.gif").scaledEncoded(175,200);
            }
            catch (IOException ex) {
                System.out.println("error encoder");
            }
            String urll=url+le.get(i).getImage();
            img = URLImage.createToStorage(enc, urll, urll, URLImage.RESIZE_SCALE);
            imgv = new ImageViewer();
            imgv.setImage(img);
            l = new SpanLabel(le.get(i).getNomAnnonce());
            l2 = new SpanLabel(le.get(i).getDescription());
            l3 = new SpanLabel(le.get(i).getType());
            lr=new SpanLabel(Double.toString(le.get(i).getPrixReducton())+"%");
            String g=Double.toString(le.get(i).getPrixReducton());
            c1=new Container(BoxLayout.y());
            c1.add(l);
            c1.add(l2);
            c1.add(l3);
            if(g!= null || !"0".equals(g)){
                c1.add(lr);
            }
            c.add(imgv);
            c.add(c1);
            c2.add(c);
            Button b = new Button();
            b.setUIID(le.get(i).getNomAnnonce());
            final Annonce event = le.get(i);
            b.addActionListener((ActionListener) (ActionEvent evt) -> {
                 f2.removeAll();
                 Container info = new Container(BoxLayout.y());
                 Image imgk = URLImage.createToStorage(enc, urll, urll, URLImage.RESIZE_SCALE);
                 ImageViewer imgv2 = new ImageViewer();
                 imgv2.setImage(imgk);
                 info.add(imgv2);
                 SpanLabel l6 = new SpanLabel(event.getNomAnnonce());
                 SpanLabel l4 = new SpanLabel(event.getDescription());
                 SpanLabel l5 = new SpanLabel(event.getType());
                 SpanLabel l44=new SpanLabel(Double.toString(event.getPrixReducton())+"%");
                 int ijk=event.getId();
                 String gg=Double.toString(event.getPrixReducton());
                 info.add(l6);
                 info.add(l4);
                 info.add(l5);
                 if(gg!= null || !"0".equals(gg)){
                 info.add(l44);
                 }
                 f2.add(info);
                    tb1.addCommandToLeftBar("Back",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                    f.show();
                    });
                    tb1.addCommandToOverflowMenu("Supprimer Annonce",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                    AnnoncesServices sss=new AnnoncesServices();
                    ArrayList<Annonce> arr=sss.getList5(event.getId());
                         AffichAnnAllClt ann= new AffichAnnAllClt();
                         ann.getF().show();
                    });
                 f2.show(); 
                
            });
            c.setLeadComponent(b);
        }
        
        f.add(c2);
           
        Toolbar tb2= f.getToolbar();
           tb2.addCommandToLeftBar("Back",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                new ProfileForm(theme).show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiAnnonce;

import Entities.Annonce;
import Services.AnnoncesServices;
import static Services.UserServices.role;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.ProfileForm;
import java.io.IOException;
import java.util.ArrayList;

public class AffichAnn2 extends Form{
    
    EncodedImage enc;
    Image img;
    ImageViewer imgv;
    public String url= "http://localhost/SoukI/web/imagesAnnonce/";
     private Database db;
    private Boolean created = false;
    
    public AffichAnn2(Resources res) {  
        super(new LayeredLayout());
        created = Database.exists("Annonce");
        try {
           db= Database.openOrCreate("Annonce");
        } catch (IOException ex) {
            System.out.println("Problème connection");
        }
        if (created == false) {
            try {
                db.execute("create table Annonce (id INTEGER,Nom TEXT,desc TEXT,Type TEXT); ");
                System.out.println(" creation ok");
            } catch (IOException ex) {
                System.out.println("Problème creation");
            }
        }
        getTitleArea().removeAll();
        getTitleArea().setUIID("Container");

        setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, true, 400));

        Tabs walkthruTabs = new Tabs();
        walkthruTabs.setUIID("Container");
        walkthruTabs.getContentPane().setUIID("Container");
        walkthruTabs.getTabsContainer().setUIID("Container");
        walkthruTabs.hideTabs();
        AnnoncesServices s =new AnnoncesServices();
        ArrayList<Annonce> le = s.getList2();
        for (Annonce le1 : le) {
            try {
                enc = EncodedImage.create("/giphy.gif").scaledEncoded(175,200);
            }
            catch (IOException ex) {
                System.out.println("error encoder");
            }
            String urll=url+le1.getImage();
            img = URLImage.createToStorage(enc, urll, urll, URLImage.RESIZE_SCALE);
            imgv = new ImageViewer();
            imgv.setImage(img);
        
        Image notes = res.getImage("notes.png");
        Image duke = res.getImage("duke.png");
        Label notesPlaceholder = new Label(le1.getNomAnnonce(), "ProfilePic");
        Label notesLabel = new Label(notes, "ProfilePic");
        Component.setSameHeight(notesLabel, notesPlaceholder);
        Component.setSameWidth(notesLabel, notesPlaceholder);
        Label bottomSpace = new Label();
        Button b = new Button("Detail >>");
        Button b2 = new Button("Services >>");
        Container tab1 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                    b,b2,
                    new Label(le1.getNomAnnonce(), "WalkthruWhite"),
                    new SpanLabel(le1.getDescription(), "WalkthruBody"),
                    imgv,
                    bottomSpace
        ));
            b.setUIID(le1.getNomAnnonce());
            final Annonce event = le1;
           Form f2 =new Form();
            b.addActionListener((ActionListener) (ActionEvent evt) -> {
                if (b.getUIID().equals(event.getNomAnnonce())) {
                    Toolbar tb2=f2.getToolbar();
                    ImageViewer imgv2= new ImageViewer();
                    SpanLabel l2 = null;
                    SpanLabel lk= null;
                    SpanLabel ll=null;
                    SpanLabel lr=null;
                    String sk;
                    Double ii=event.getPrixReducton();
                    try {
                        EncodedImage enc2;
                        enc2 = EncodedImage.create("/giphy.gif");
                        Label ss2 = new Label(event.getImage());
                        Image img2 = URLImage.createToStorage(enc2, "imagea" + event.getImage(), url + ss2.getText());
                        imgv2.setImage(img2);
                        lr= new SpanLabel(event.getNomAnnonce()+"\n");
                        lk=new SpanLabel(event.getType());
                        sk=Double.toString(event.getPrixReducton());
                        ll=new SpanLabel(sk+"%");
                        l2 = new SpanLabel(event.getDescription());
                    }
                    catch(IOException ex){}
                    Font existingFont = l2.getUnselectedStyle().getFont();
                    Font newFont = Font.createSystemFont(existingFont.getFace(), Font.STYLE_ITALIC, Font.SIZE_SMALL);
                    l2.getUnselectedStyle().setFont(newFont);
                    Container c2=new Container(new FlowLayout(CENTER));
                    c2.add(imgv2);
                    c2.add(lr);
                    Container cc=new Container(BoxLayout.x());
                    cc.add(lk);
                    if(ii!=0){
                        cc.add(ll);
                    }
                    c2.add(cc);
                    c2.add(l2);
                    f2.add(c2);
                    f2.show();
                    tb2.addCommandToLeftBar("Back",res.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                       new AffichAnn2(res).show();
                    });
                    if(!role.equals("[ROLE_ARTISAN, ROLE_USER]")){
                    /********************************************Ajout***************************************************/
                    tb2.addCommandToOverflowMenu("Ajout Favori", res.getImage("back-command.png"), (ActionListener) (ActionEvent evt2) -> {
                    String ss= event.getNomAnnonce();
                    String sss=event.getDescription();
                    String ssss=event.getType();
                    int ij=event.getId();
                    try {
//                        Cursor curs = db.executeQuery("select * from Annonce where nom='"+event.getNomAnnonce()+"';");
//                            while (curs.next()) {
//                                Row r = curs.getRow();
//                                String kl=Integer.toString(r.getInteger(0));
//                                 if(!kl.equals("")){
                                     db.execute("insert into Annonce (id,nom,desc,Type ) values ('" + ij + "','" + ss + "','" + sss + "','" + ssss + "');");
                                     Local_Notification lo=new Local_Notification("Ajout","Ajout Réussi !");
                                     lo.getLocal_Notif();
                                     System.out.println("insertion");
//                                 }
//                            }
                        }
                    catch (IOException ex) {
                            System.out.println("pas insertion");
                    }
                    });
                    }
                    /******************************************************************************************************/
                    tb2.addCommandToOverflowMenu("Favoris", res.getImage("back-command.png"), (ActionListener) (ActionEvent evt3) -> {
                    Form f5 = new Form(BoxLayout.y());
                    try {
                            Cursor curs = db.executeQuery("select * from Annonce;");
                            while (curs.next()) {
                                Row r = curs.getRow();
                                String k=Integer.toString(r.getInteger(0));
                                String kk = r.getString(1);
                                String kkk = r.getString(2);
                                String kkkk = r.getString(3);
                                SpanLabel ssp=new SpanLabel("***************");
                                SpanLabel Sp= new SpanLabel(kk);
                                SpanLabel Sp2=new SpanLabel(kkk);
                                SpanLabel Sp3=new SpanLabel(kkkk);
                                Container cccc=new Container(BoxLayout.y());
                                cccc.add(ssp);
                                cccc.add(Sp);
                                cccc.add(Sp2);
                                cccc.add(Sp3);
                                f5.add(cccc);
                            }
                        }
                    catch (IOException ex) {
                            System.out.println("Affichage pas bon ");
                    }
                        f5.show();
                        Toolbar tb5= f5.getToolbar();
                        tb5.addCommandToLeftBar("Back",res.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                        f2.show();
                        });
                    });
                     /******************************************************************************************************/
                    if(!role.equals("[ROLE_ARTISAN, ROLE_USER]")){
                    tb2.addCommandToOverflowMenu("Supprimer des favo", res.getImage("back-command.png"), (ActionListener) (ActionEvent evt3) -> {
                     try {
                         db.execute("Delete from Annonce where id='"+event.getId()+"';");
                         System.out.println("sup");
                     } catch (IOException ex) {
                        System.out.println("Error sup");
                     }
                    });
                    }
                   }
                
            });
            Form f3= new Form("Services",new FlowLayout(Component.CENTER,Component.CENTER));
        b2.addActionListener((ActionListener) (ActionEvent evt) -> {
            Button bAvis = new Button("Donner Avis Service Annonce");
            bAvis.addActionListener((ActionListener) (ActionEvent evt1) -> { 
                AvisAnnonce Avis=new AvisAnnonce();
                Avis.getF().show();
            });
            Button bComment = new Button("Commenter Service Annonce");
            bComment.addActionListener((ActionListener) (ActionEvent evt1) -> { 
                CommentaireAnn Avis=new CommentaireAnn();
                Avis.getF().show();
            });
            Button bPartageFb = new Button("Partager sur Fb");
            bPartageFb.addActionListener((ActionListener) (ActionEvent evt1) -> { 
                FbServices fb=new FbServices();
                fb.getF().show();
            });
            Button bRecherche= new Button("Recherche");
            bRecherche.addActionListener((ActionListener) (ActionEvent evt1) -> {      
                 Recherche Avis=new Recherche();
                Avis.getF().show();
            });
            Container c= new Container(BoxLayout.y());
            c.add(bRecherche);
            c.add(bPartageFb);
            c.add(bAvis);
            c.add(bComment);
            f3.add(c);
            f3.show();
            Toolbar tb3 = f3.getToolbar();
            tb3.addCommandToLeftBar("Back",res.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                new AffichAnn2(res).show();
            });
        });
            tab1.setUIID("WalkthruTab1");
            walkthruTabs.addTab("", tab1);
        }
          add(walkthruTabs);

        ButtonGroup bg = new ButtonGroup();
        Image unselectedWalkthru = res.getImage("unselected-walkthru.png");
        Image selectedWalkthru = res.getImage("selected-walkthru.png");
        RadioButton[] rbs = new RadioButton[walkthruTabs.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(CENTER);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        walkthruTabs.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        getAllStyles().setBgColor(0xFFFFFF);
        Button skip = new Button("Acceuil");
        skip.setUIID("SkipButton");
        skip.addActionListener(e -> new ProfileForm(res).show());
        Container southLayout = BoxLayout.encloseY(
                radioContainer,
                skip
        );
        add(BorderLayout.south(
                southLayout
        ));
       
    }
}

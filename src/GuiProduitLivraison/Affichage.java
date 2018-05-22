/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiProduitLivraison;

import Entities.Produit;
import Services.CRUDPrLiv;
import static Services.UserServices.role;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.ProfileForm;
import java.io.IOException;


/**
 *
 * @author haythem
 */
public class Affichage extends Form {
    EncodedImage enc;
    Image img;
    ImageViewer imgv;
    public String url= "http://localhost/SoukI/web/images3/";
    SpanLabel lb;
    Button btnajout,affichepanier;
    Resources res2;
    float somme;
    public Affichage(Resources res) {
        res2 = UIManager.initFirstTheme("/theme_1");
        getTitleArea().removeAll();
        getTitleArea().setUIID("Container");
        setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, true, 400));
        Tabs walkthruTabs = new Tabs();
        walkthruTabs.setUIID("Container");
        walkthruTabs.getContentPane().setUIID("Container");
        walkthruTabs.getTabsContainer().setUIID("Container");
        walkthruTabs.hideTabs();
        CRUDPrLiv c =new CRUDPrLiv();
        ArrayList<Produit> lis=c.getallrdv();
           for (int i = 0; i < lis.size(); i++) {
                try {
                enc = EncodedImage.create("/giphy.gif").scaledEncoded(175,200);
            }
            catch (IOException ex) {
                System.out.println("error encoder");
            }
            String urll=url+lis.get(i).getNomImage();
            img = URLImage.createToStorage(enc, urll, urll, URLImage.RESIZE_SCALE);
            imgv = new ImageViewer();
            imgv.setImage(img);
            final int ii=lis.get(i).getId();
               Image notes = res.getImage("notes.png");
               Image duke = res.getImage("duke.png");
               Label notesPlaceholder = new Label(lis.get(i).getNomProduit(), "ProfilePic");
               Label notesLabel = new Label(notes, "ProfilePic");
               Component.setSameHeight(notesLabel, notesPlaceholder);
               Component.setSameWidth(notesLabel, notesPlaceholder);
               Label bottomSpace = new Label();
               
             btnajout = new Button("ajouter au panier");
              Button b2 = new Button("Voir Panier >>");
              Button b3 =new Button("Confirmer Commande");
              if(role.equals("[ROLE_CLIENT, ROLE_USER]")){ 
              btnajout.addActionListener((ActionListener) (ActionEvent eve) -> {
                   CRUDPrLiv rs =new  CRUDPrLiv();
                   rs.ajouter(ii);
                   Dialog.show("Succée", "Produit ajouté", "OK", null);
               });
               b2.addActionListener((ActionListener) (ActionEvent evt) -> {
                   Affichagecmd affcmd =new Affichagecmd();
                   affcmd.getF().show();
                });
              }
              
              if(!role.equals("[ROLE_CLIENT, ROLE_USER]")){ 
              b3.addActionListener((ActionListener) (ActionEvent evt) -> {
                  livraisonartisant livart=new livraisonartisant();
                  livart.getF().show();
                });
              }
              
               Container tab1 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                       btnajout,b2,b3,
                    new Label(lis.get(i).getNomProduit(), "WalkthruWhite"),
                    new SpanLabel(lis.get(i).getDescription(), "WalkthruBody"),
                    new SpanLabel(lis.get(i).getRegion(), "WalkthruBody"),
                    new SpanLabel(Double.toString( lis.get(i).getPrix()), "WalkthruBody"),
                    imgv,
                    bottomSpace
               ));
               tab1.setUIID("WalkthruTab1");
            walkthruTabs.addTab("", tab1);
        }
//            affichepanier = new Button("voir panier"); 
//          //***************************************
//           affichepanier.addActionListener((e)->{
//        Affichagecmd a=new Affichagecmd();
//         a.getF().show();
//        });     
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
        skip.addActionListener(e -> 
                new ProfileForm(res).show());
        Container southLayout = BoxLayout.encloseY(
                radioContainer,
                skip
        );
        add(BorderLayout.south(
                southLayout
        ));
    }

    
}

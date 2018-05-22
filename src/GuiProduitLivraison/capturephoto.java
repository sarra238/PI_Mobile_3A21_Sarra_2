

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiProduitLivraison;

//import Entity.Produit;
//import Services.CRUD;
import com.codename1.capture.Capture;
//import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
//import com.codename1.ui.Button;
//import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.events.ActionEvent;
//import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
//import java.util.ArrayList;
import com.codename1.ui.util.Resources;
import java.io.IOException;
//import static jdk.nashorn.internal.runtime.Debug.id;
//import sun.font.FontLineMetrics;


/**
 *
 * @author haythem
 */
public class capturephoto {
   // FontImage Image;
    Image camera;
    Label picture;
    Form hi;
    Style s;
    Image capturedImage,roundMask ;
    Graphics gr;
    Object mask;
    Resources theme;

  //  SpanLabel lb;
    // Button btnajout,affichepanier;
  Resources res;
 // float somme;
    public capturephoto() {
        
 Toolbar.setGlobalToolbar(true);
 hi = new Form("Rounder", new BorderLayout());
 theme = UIManager.initFirstTheme("/theme_1");
       Toolbar tb=hi.getToolbar();
       tb.addCommandToLeftBar("Back",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                       Affichagecmd affcmd=new Affichagecmd();
                       affcmd.getF().show();
                    });
 picture = new Label("", "Container");
hi.add(BorderLayout.CENTER, picture);
hi.getUnselectedStyle().setBgColor(0xff0000);
hi.getUnselectedStyle().setBgTransparency(255);
 s = UIManager.getInstance().getComponentStyle("TitleCommand");
 camera = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, s);
hi.getToolbar().addCommandToRightBar("", camera, (ev) -> {
    try {
        int width = Display.getInstance().getDisplayWidth();
        capturedImage = Image.createImage(Capture.capturePhoto(width, -1));
         roundMask = Image.createImage(width, capturedImage.getHeight(), 0xff000000);
         gr = roundMask.getGraphics();
        gr.setColor(0xffffff);
        gr.fillArc(0, 0, width, width, 0, 360);
         mask = roundMask.createMask();
        capturedImage = capturedImage.applyMask(mask);
        picture.setIcon(capturedImage);
        hi.revalidate();
        hi.add(capturedImage);
    } catch(IOException err) {
        Log.e(err);
    }
});

    }
public Form getF() {
        return hi;
    }

    public void setF(Form hi) {
        this.hi = hi;
    }
    } 
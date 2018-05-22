/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiProduitLivraison;

import com.codename1.components.SpanLabel;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.Form;
import java.io.IOException;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;



public class GoogleMap {
    Form hi;
    private Form current;
    double lat = 0;
    double lng = 0;
    private Object res;
    private final Resources theme;
    

 /*   public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        
       */
        
        
   public  GoogleMap(double latt,double laon) throws IOException {
       hi = new Form("SOUK El MEDINA ");
       theme = UIManager.initFirstTheme("/theme_1");
       Toolbar tb=hi.getToolbar();
       tb.addCommandToLeftBar("Back",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                       Affichagecmd affcmd=new Affichagecmd();
                       affcmd.getF().show();
                    });

    MapComponent map = new MapComponent();
                        Image img8 = Image.createImage("/gg.png");

                        double lat = latt;
                        double longg = laon;
                       System.err.println(lat);
                        System.err.println(longg);
                      //  System.out.println(event.getLat());
                        Coord coordonnees = new Coord(lat, longg);

                        PointLayer pl = new PointLayer(coordonnees, "", img8);
                        pl.setDisplayName(true);
                        PointsLayer pointsL = new PointsLayer();
                        pointsL.addPoint(pl);
                        pointsL.setPointIcon(img8);
                        map.addLayer(pointsL);
                        Label spmap = new Label();
                        spmap.setText("Emplacement");
                        Font existingFont = spmap.getUnselectedStyle().getFont();
                        Font newFont2 = Font.createSystemFont(existingFont.getFace(), Font.STYLE_BOLD, Font.SIZE_SMALL);
                        spmap.getUnselectedStyle().setFont(newFont2);
                        spmap.getAllStyles();
                        spmap.setUIID("labelBlue");
                        SpanLabel spmap1 = new SpanLabel();

                        Container cmap = new Container();
                        cmap.add(map);
                        hi.add(spmap);
                        hi.add(spmap1);
                        //hi.add(pointsL);

                        hi.add(cmap);
   }
   public Form getF() {
        return hi;
    }

    public void setF(Form hi) {
        this.hi = hi;
    }
    
}

/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package GuiEvenement;

import Entities.Evenement;
import Entities.commentaire;
import Entities.notif;
import Entities.particEv;
import Services.EvenementServices;
import Services.PartService;
import static Services.UserServices.role;
import static Services.UserServices.uname;
import Services.commService;
import Services.notifService;
import Services.reserEvService;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ImageViewer;
import com.codename1.components.MediaPlayer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Database;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.ProfileForm;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A swipe tutorial for the application
 *
 * @author Shai Almog
 */
public class evenementL extends Form {

    public String url = "http://localhost/SoukI/web/images2/";
    private ImageViewer imgv;
    private Database db;
    private boolean created = false;
    SpanLabel l2;
    public static int j;
    Form co;
Container notco ;
    public evenementL(Resources res) throws IOException {

        super(new LayeredLayout());

        getTitleArea().removeAll();
        getTitleArea().setUIID("Container");

        setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, true, 400));

        Tabs walkthruTabs = new Tabs();
        walkthruTabs.setUIID("Container");
        walkthruTabs.getContentPane().setUIID("Container");
        walkthruTabs.getTabsContainer().setUIID("Container");
        walkthruTabs.hideTabs();
        EvenementServices s = new EvenementServices();
        ArrayList<Evenement> le = s.getList2();

        created = Database.exists("pi7");
        try {
            db = Database.openOrCreate("pi7");
            if (created == false) {
                db.execute("create table participation (id INTEGER,type TEXT,ide INTEGER,idU INTEGER);");
            }
        } catch (IOException ex) {
        }
        PartService cprr = new PartService();
        for (int i = 0; i < le.size(); i++) {
final Evenement event = le.get(i);
            EncodedImage enc = EncodedImage.create("/giphy.gif");
            Label ss = new Label(le.get(i).getNomImg());
            Image img = URLImage.createToStorage(enc, "imagea" + event.getNomImg(), url + ss.getText(), URLImage.RESIZE_SCALE);
            imgv = new ImageViewer(img);
            Image notes = res.getImage("notes.png");
            Image duke = res.getImage("duke.png");

            Label notesPlaceholder = new Label(le.get(i).getNomEvenement(), "ProfilePic");
            Label notesLabel = new Label(notes, "ProfilePic");
            Component.setSameHeight(notesLabel, notesPlaceholder);
            Component.setSameWidth(notesLabel, notesPlaceholder);
            Label bottomSpace = new Label();
         
             
             System.out.println(role+ ((role.equals("[ROLE_ARTISAN, ROLE_USER]"))==true));
              
                 
          
            Button notif = new Button("");
       notif.setUIID("notif");
         Form notfo= new Form(new BoxLayout(BoxLayout.Y_AXIS));
                   
      FontImage.setMaterialIcon(notif, FontImage.MATERIAL_NOTIFICATIONS, 5);
      notif.getAllStyles().setBgColor(0xFFFFFFFF);
      
      notif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {  notifService nst = new notifService();
          ArrayList<notif> nl= nst.getList2();
            for(int not=0; not<nl.size();not++){
                notco = new Container();
                Label notifl = new Label(nl.get(not).getText());
                notco.add(notifl);
                notfo.add(notco);
                
            }
            notfo.show();
           notfo.getToolbar().addCommandToLeftBar("back", null, (e -> {
                       try {
                           new  evenementL(res).show();
                       } catch (IOException ex) {
                                }
                            
                        })
                        );
            }
        });
                
         
            Button b = new Button("Detail >>");

            Container tab1 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                    b,
                    new Label(le.get(i).getNomEvenement(), "WalkthruWhite"),
                    new SpanLabel(le.get(i).getDescription(), "WalkthruBody"),
                    imgv,
                    bottomSpace,notif
            ));
         
            b.setUIID(le.get(i).getNomEvenement());
            

             
            b.addActionListener((ActionListener) (ActionEvent evt) -> {
                System.out.println("hello" + event.toString());
                if (b.getUIID().equals(event.getNomEvenement())) {
                    Form f2 = new Form();

                    final Form hi = new Form("MediaPlayer", new BorderLayout());
                  
          hi.setToolbar(new Toolbar());
                    Style sss = UIManager.getInstance().getComponentStyle("Title");
                    FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_VIDEO_LIBRARY, sss);

                    FileSystemStorage fs = FileSystemStorage.getInstance();
                    String recordingsDir = fs.getAppHomePath() + "recordings/";
                    fs.mkdir(recordingsDir);
                    hi.getToolbar().addCommandToRightBar(new Command("", icon) {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Display.getInstance().openGallery((e) -> {
                                if (e != null && e.getSource() != null) {
                                    String file = (String) e.getSource();

                                    try {
                                        Media video = MediaManager.createMedia(file, true);

                                        hi.removeAll();
                                        hi.add(BorderLayout.CENTER, new MediaPlayer(video));
                                        hi.revalidate();
                                        //video.play();

                                        sendToNetwork(false, file);

                                    } catch (IOException err) {
                                        Log.e(err);
                                    }
                                }
                            }, Display.GALLERY_VIDEO);
                        }
                    });
                  // hi.show();

                    //   try {
                    PartService p = new PartService();
                    ArrayList<particEv> les = p.affichpar(event.getId());
                    int len = les.size();
                    System.out.println(len);
                    //Cursor cr =db.executeQuery("Select count(*) from participation");

                    //   Row r= cr.getRow();
                    //  int nb = r.getInteger(0);
                    //  System.out.println(nb);
                    try {
                        EncodedImage enc2;
                        enc2 = EncodedImage.create("/giphy.gif");
                        Label ss2 = new Label(event.getNomImg());
                        Image img2 = URLImage.createToStorage(enc2, "imagea" + event.getNomImg(), url + ss2.getText());
                        ImageViewer imgv2;
                        imgv2 = new ImageViewer(img2);
                        l2 = new SpanLabel(" " + event.getDateDeb() + "  " + event.getDateFin() + "\n " + event.getDescription()
                        );
                        Font existingFont = l2.getUnselectedStyle().getFont();
                        Font newFont = Font.createSystemFont(existingFont.getFace(), Font.STYLE_ITALIC, Font.SIZE_SMALL);
                        l2.getUnselectedStyle().setFont(newFont);
                        Container c2;
                        c2 = new Container(new FlowLayout(CENTER));
                        c2.add(imgv2);
                        c2.add(l2);
                        //   Container c3=new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        RadioButton cB = new RadioButton("interesé(e)");
                        RadioButton cB2 = new RadioButton("nest pas interesé(e)");
                        RadioButton cB3 = new RadioButton("participer");
                        c2.add(cB);
                        c2.add(cB2);
                        c2.add(cB3);
                        f2.add(c2);

                        MapComponent map = new MapComponent();

                        Image img8 = Image.createImage("/gg.png");

                        double lat =event.getLat();
                      
                        double longg =event.getLang();
                          System.out.println(lat+"longitude="+longg);
                        Coord coordonnees = new Coord(lat, longg);

                        PointLayer pl = new PointLayer(coordonnees, event.getNomEvenement(), img8);
                        pl.setDisplayName(true);
                        PointsLayer pointsL = new PointsLayer();
                        pointsL.addPoint(pl);
                        pointsL.setPointIcon(img8);
                        map.addLayer(pointsL);
                        Label spmap = new Label();
                        spmap.setText("Emplacement");
                        Font existingFont2 = spmap.getUnselectedStyle().getFont();
                        Font newFont2 = Font.createSystemFont(existingFont.getFace(), Font.STYLE_BOLD, Font.SIZE_SMALL);
                        spmap.getUnselectedStyle().setFont(newFont2);
                        spmap.getAllStyles();
                        spmap.setUIID("labelBlue");
                        SpanLabel spmap1 = new SpanLabel();

                        Container cmap = new Container();
                        cmap.add(map);
                        f2.add(spmap);
                        f2.add(spmap1);

                        f2.add(cmap);
Button stat = new Button("stat");
f2.add(stat);
stat.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) { ArrayList<String> values = new ArrayList<String>();
       values.add("khou");
        values.add("ben");      
        // Set up the renderer
        int[] colors = new int[]{0xE12336,0xF69602, 0xF69602, 0xF69602, 0x008A4F};

        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(15);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, 0x224895);
        r.setGradientStop(0, 0x224895);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Evenement",event.getId()), renderer);
        Container titlechart = new Container(new FlowLayout(Component.CENTER,Component.CENTER));
       Label ltit = new Label("Evenement");
        ltit.getAllStyles().setFgColor(0xF69602);
          Label ltit2 = new Label("Evenement");
        ltit2.getAllStyles().setFgColor(0xF69602);
        titlechart.add(ltit);
       //titlechart.add(ltit2);
        // Wrap the chart in a Component so we can add it to a form
        
        Form fs = new Form();
        ChartComponent cs = new ChartComponent(chart); 
      fs.add(titlechart); 
      fs.add(cs);
      fs.show();
    fs.getToolbar().addCommandToLeftBar("back", null, (e -> {
        f2.show();
}));
                            
                            }
                        });
                        if (len == 0) {
                            cB.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                cB3.setSelected(false);
                                cB2.setSelected(false);
                                /* try {
                                db.execute("insert into participation (type,ide,idU) values ('" + cB.getText() + "','" + event.getId() + "',20);");
                                System.out.println("ok");
                                } catch (IOException ex) {
                                }*/
                                PartService p1 = new PartService();
                                particEv v = new particEv(event.getId(), "interesé(e)");
                                p1.ajoutPar(v);
                            });
                            cB2.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                cB.setSelected(false);
                                cB3.setSelected(false);
                                /*try {
                                db.execute("insert into participation (type,ide,idU) values ('" + cB2.getText() + "','" + event.getId() +"','"+conn+"');");
                                System.out.println("ok");
                                } catch (IOException ex) {
                                }*/
                                PartService p1 = new PartService();
                                particEv v = new particEv(event.getId(), "nest pas interesé(e)");
                                p1.ajoutPar(v);
                            });

                            cB3.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                cB.setSelected(false);
                                cB2.setSelected(false);
                                /*
                                  db.execute("insert into participation (type,ide,idU) values ('" + cB3.getText() + "','" + event.getId() + "','"+conn+"');");

                                    System.out.println("ok");
                                } */
                                PartService p1 = new PartService();
                                particEv v = new particEv(event.getId(), "participer");
                                p1.ajoutPar(v);
                                System.out.println(event.getType());
                                if ("formation".equals(event.getType())){
                  
                                    Dialog.show("remarques","pour confirmer votre demande priés d'appeler le responsable", "ok","");
                                
                                reserEvService rs = new reserEvService();
                                rs.ajoutPar(event.getId());
                                        
                                }});
                        } else {
                            PartService p2 = new PartService();
                            ArrayList<particEv> les2 = p.affichpar(event.getId());
                            for (int i1 = 0; i1 < les2.size(); i1++) {
                                if (les.get(i1).getType().equals(cB.getText())) {
                                    cB.setSelected(true);
                                    cB2.setSelected(false);
                                    cB3.setSelected(false);
                                    cB.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                        cB.setSelected(true);
                                        cB2.setSelected(false);
                                        cB3.setSelected(false);
                                        PartService p3 = new PartService();
                                        particEv v1 = new particEv(event.getId(), cB.getText());
                                        p3.modifPar(v1);
                                      
                                      //  rf.supp(event.getId(),conn);
                                    });
                                    cB2.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                        cB.setSelected(false);
                                        cB2.setSelected(true);
                                        cB3.setSelected(false);
                                        PartService p3 = new PartService();
                                        particEv v1 = new particEv(event.getId(), cB2.getText());
                                        p3.modifPar(v1);
                                      
                                    });
                                    cB3.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                        cB.setSelected(true);
                                        cB2.setSelected(true);
                                        cB3.setSelected(false);
                                        PartService p3 = new PartService();
                                        particEv v1 = new particEv(event.getId(), cB3.getText());
                                        p3.modifPar(v1);
                                        if ("formation".equals(event.getType())){
                  
                                    Dialog.show("remarques","pour confirmer votre demande priés d'appeler le responsable", "ok","");
                                
                                           reserEvService newr= new reserEvService();
                                        newr.ajoutPar(event.getId());
                                        }  });
                                }
                                if (les.get(i1).getType().equals(cB2.getText())) {
                                    cB.setSelected(false);
                                    cB2.setSelected(true);
                                    cB3.setSelected(false);
                                    cB.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                        cB.setSelected(true);
                                        cB2.setSelected(false);
                                        cB3.setSelected(false);
                                        PartService p3 = new PartService();
                                        particEv v1 = new particEv(event.getId(), cB.getText());
                                        p3.modifPar(v1);
                                    });
                                    cB2.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                        cB.setSelected(false);
                                        cB2.setSelected(true);
                                        cB3.setSelected(false);
                                        PartService p3 = new PartService();
                                        particEv v1 = new particEv(event.getId(), cB2.getText());
                                        p3.modifPar(v1);
                                    });
                                    cB3.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                        cB.setSelected(false);
                                        cB2.setSelected(false);
                                        cB3.setSelected(true);
                                        PartService p3 = new PartService();
                                        particEv v1 = new particEv(event.getId(), cB3.getText());
                                        p3.modifPar(v1);
                                        reserEvService newr= new reserEvService();
                                        newr.ajoutPar(event.getId());
                                    });
                                }
                                if (les.get(i1).getType().equals(cB3.getText())) {
                                    cB.setSelected(false);
                                    cB2.setSelected(false);
                                    cB3.setSelected(true);
                                    cB.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                        cB.setSelected(true);
                                        cB2.setSelected(false);
                                        cB3.setSelected(false);
                                        PartService p3 = new PartService();
                                        particEv v1 = new particEv(event.getId(), cB.getText());
                                        p3.modifPar(v1);
                                            reserEvService rf2 = new reserEvService();
                                        rf2.suppM(event.getId());
                                    });
                                    cB2.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                        cB.setSelected(false);
                                        cB2.setSelected(true);
                                        cB3.setSelected(false);
                                        PartService p3 = new PartService();
                                        particEv v1 = new particEv(event.getId(), cB2.getText());
                                        p3.modifPar(v1);
                                            reserEvService rf3 = new reserEvService();
                                        rf3.suppM(event.getId());
                                    });
                                    cB3.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                        cB.setSelected(false);
                                        cB2.setSelected(false);
                                        cB3.setSelected(true);
                                        PartService p3 = new PartService();
                                        particEv v1 = new particEv(event.getId(), cB3.getText());
                                        p3.modifPar(v1);
                                    });
                                }
                                System.out.println(les.get(i1).getType());
                            }
                        }
                        Button sup = new Button();
                        sup.addActionListener((ActionListener) (ActionEvent evt1) -> {
                            try {
                                db.execute("drop table participation ;");
                            } catch (IOException ex) {
                            }
                        });
                        f2.add(sup);
                        TextField com = new TextField();
                        com.setHint("commenter");
                        com.getAllStyles().setFgColor(0x00000000);
                        Button commenter = new Button("Commenter");

                        // TextField com2 = new TextField();
                        commService cs = new commService();
                        ArrayList<commentaire> clist = cs.affichcom(event);
                        f2.add(com);
                        f2.add(commenter);
                        Container ccom = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                        for (commentaire clist1 : clist) {
                            SpanLabel com2 = new SpanLabel();
                            com2.setText(uname + ":  " + clist1.getComm());
                            com2.getAllStyles().setFgColor(0x00000);

                            ccom.add(com2);
                        }

                        f2.add(ccom);
                        commenter.addActionListener((ActionListener) (ActionEvent evt1) -> {

                            commService p3 = new commService();
                            commentaire com1 = new commentaire(event.getId(), com.getText());
                            p3.ajoutCom(com1);

                            LocalNotification n = new LocalNotification();
                            n.setId("demo-notification");
                            n.setAlertBody("It's time to take a break and look at me");
                            n.setAlertTitle("Break Time!");
                            //   n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound
                            System.out.println(n.toString());

                            Display.getInstance().scheduleLocalNotification(
                                    n,
                                    System.currentTimeMillis() + 10 * 1000, // fire date/time
                                    LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
                            );
                            //f2.show();

                        });
                       f2.show();
                        f2.getToolbar().addCommandToLeftBar("back", null, (e -> {
                            try {
                                new evenementL(res).show();
                            } catch (IOException ex) {
                            }
                        })
                        );
                    } catch (IOException ex) {
                    }
                }
            } //   lb.setText(s.getList2().toString());
            );

            tab1.setUIID("WalkthruTab1");

            walkthruTabs.addTab("WalkthruWhite", tab1);
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
     
        
        Button skip = new Button("exit");
        skip.setUIID("SkipButton");
        
        skip.addActionListener(e -> new ProfileForm(res).show());
 
  
        Container southLayout = BoxLayout.encloseY(
                radioContainer,
                skip
              
      
        );
        
        add(BorderLayout.south(
                southLayout
        ));
   
    
              
          
   //FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_VIDEO_LIBRARY, notif);
            }

    public void sendToNetwork(boolean timeElapsed, String file) throws IOException {
        try {
            MultipartRequest request = new com.codename1.io.MultipartRequest() {
                
            };
            request.setUrl("http://localhost/SoukI/web/img");
            request.setPost(true);

            //add the data image
            request.addData("data", file, "video/mp4");
            request.setFilename("data", "/khouloud.mp4");
            System.out.println("oui");
            NetworkManager.getInstance().addToQueue(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
     private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(20);
        renderer.setLabelsColor(0xE12336);
        renderer.setLegendTextSize(0);
        
        renderer.setMargins(new int[]{50, 50, 50, 50});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

   
    protected CategorySeries buildCategoryDataset(String title,int g) {
        CategorySeries series = new CategorySeries(title);
       
      
            try {
                PartService pr = new PartService();
            
       
         int a=pr.Participant(g);
         int b =pr.affichparEv(g);
         int c=pr.affichnnEv(g);
         
        
         
               
               
        
         System.out.println("participant : "+a);
     System.out.println("interessés : "+b);
            series.add("participer",(int) a);
             series.add("nest pas interessé(e)",(int) c);
             series.add("interessé(e)",(int) b);
            } catch (ArithmeticException e) {
            }
            
       

        return series;
    }


}

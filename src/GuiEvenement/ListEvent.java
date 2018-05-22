/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiEvenement;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import Services.EvenementServices;
import Services.PartService;
import Entities.Evenement;
import Entities.commentaire;
import Entities.particEv;
import Entities.reservationEvent;
import static Services.UserServices.conn;
import static Services.UserServices.uname;
import Services.commService;
import Services.notifService;
import Services.reserEvService;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.messaging.Message;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.uikit.materialscreens.ProfileForm;
import java.io.IOException;
import java.util.ArrayList;

public class ListEvent extends Form{

    Form f;
    SpanLabel lb;
    TextField nom;
    SpanLabel l;
    SpanLabel l2;
    Container c;
    private Image img;
    private Resources theme;
    public String msg;
    private Database db;
    private boolean created = false;
    public static int j;
    public String url = "http://localhost/SoukI/web/images2/";
private Container crr;
    private EncodedImage enc;
    private ImageViewer imgv;
    private Label ss;
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Emplacement", "commentaires", "Evenement"};
public Form co;
    public ListEvent() {
   theme = UIManager.initFirstTheme("/theme_1");
        f = new Form("Evenement", new FlowLayout());
        EvenementServices s = new EvenementServices();
        ArrayList<Evenement> le = s.getList3(conn);

        created = Database.exists("pi");
        try {
            db = Database.openOrCreate("pi");
            if (created == false) {
                db.execute("create table participation (id INTEGER,type TEXT,ide INTEGER,idU INTEGER);");
            }
        } catch (IOException ex) {
        }
        for (int i = 0; i < le.size(); i++) {
               final Evenement event = le.get(i);
            c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {
                System.out.println("error encoder");
            }
            ss = new Label(le.get(i).getNomImg());

            img = URLImage.createToStorage(enc, "imagea" + event.getNomImg(), url + ss.getText(), URLImage.RESIZE_SCALE);
            imgv = new ImageViewer(img);
            l = new SpanLabel(le.get(i).getNomEvenement());
            l.getAllStyles().setFgColor(0x0000FF);
            l.getAllStyles().setBgColor(0xFF0000);
            c.add(l);
            c.add(imgv);
            Button b = new Button("Detail >>");
            
            b.getAllStyles().setBgColor(COLORS[0]);
            c.add(b);
            f.add(FlowLayout.encloseCenter(c));

            b.setUIID(le.get(i).getNomEvenement());
         

            b.addActionListener((ActionListener) (ActionEvent evt) -> {
            //    System.out.println("hello" + event.toString());
                if (b.getUIID().equals(event.getNomEvenement())) {
                    Form f2 = new Form();
                    
                         Button confi = new Button("confirmer reservation");
                          f2.add(confi);
                      co = new Form();
                      Button annu = new Button("annuler");
                            annu.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    EvenementServices em = new EvenementServices();   
                                      notifService nse= new notifService();
                                      PartService chhh = new PartService();
                                      
                                      ArrayList<particEv> annup= chhh.affichttpar(event.getId());
                                      for (int bb=0; bb<annup.size();bb++){
                                          nse.ajoutNot2("event annuler",bb );
                                      }
                                    
                                em.supp(event.getId());
                            
                                
                                
                                }
                            });
                                f2.add(annu);
              
                 confi.addActionListener((ActionListener) (ActionEvent evt1) -> {
                           //  System.out.println("hello word");
                            
                             reserEvService rser = new reserEvService();
                             ArrayList<reservationEvent> reservation = rser.getList2(event.getId());
                           //     System.out.println(reservation.size());
                                notifService ns = new notifService();
                                ns.ajoutNot(event.getNomEvenement());
                             
                                
                        for (int t =0;t < reservation.size(); t++) {
                            crr= new Container();
                           
                            Label reser = new Label(reservation.get(t).getNom() + " " 
                                    + reservation.get(t).getPrenom() + " " + reservation.get(t).getMail() 
                                    + " " );
                            System.out.println("test"+reser.getText()+reservation.size());
                             Button supp = new Button("");
                                Button confirmation = new Button("");
                final reservationEvent resEv = reservation.get(t);
                             confirmation.setUIID(reservation.get(t).getNom());
             supp.setUIID(reservation.get(t).getMail());
                      FontImage.setMaterialIcon(confirmation, FontImage.MATERIAL_CHECK_CIRCLE, 5);
                        FontImage.setMaterialIcon(supp, FontImage.MATERIAL_CANCEL, 5);
                      //    confirmation.getAllStyles().setBgColor(0x006400);
                       //   supp.getAllStyles().setBgColor(0xDC143C);
                            confirmation.addActionListener((ActionListener) (ActionEvent evt2) -> {
                               if (confirmation.getUIID().equals(resEv.getNom())) {
                                reserEvService rf= new reserEvService();
                                System.out.println(resEv.getId());
                                rf.conf(resEv.getId());
                              //  String Us =  rf.getUsr(resEv.getId());
                          
                                //    Label vmail = new Label (Us);
                                //    System.out.println(vmail.getText());
                                  Message m = new Message("vous etes inscrit a la foramtion "+event.getNomEvenement()+"le "+ event.getDateDeb()+"soyez le bienvenu");

                                Display.getInstance().sendMessage(new String[]{resEv.getMail()}, "confirmation", m);   
                                

                            
                          }});
                            supp.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt3) {
                                     if (supp.getUIID().equals(resEv.getMail())) {
                                    reserEvService rf= new reserEvService();
                                    rf.supp(resEv.getId());
                                  
                                }}
                            });
                            
                                
                            
                            crr.add(reser);
                            crr.add(supp);
                            crr.add(confirmation);
                            co.add(crr);
                        }
                        co.show();
                        co.getToolbar().addCommandToLeftBar("back", null, (e -> {
                            ListEvent li = new ListEvent();
                            li.getF().show();
                            
                        })
                        );
                    });
                   
                         
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
                       
                        f2.add(c2);

                        MapComponent map = new MapComponent();

                        Image img8 = Image.createImage("/gg.png");

                        double lat = event.getLat();
                        double longg = event.getLang();
                        System.out.println(event.getLat());
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
        renderer.setChartTitleTextSize(20);
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
                       
                        
                     
                      

                        // TextField com2 = new TextField();
                        commService cs = new commService();
                        ArrayList<commentaire> clist = cs.affichcom(event);
                  
                        Container ccom = new Container(new FlowLayout(CENTER));

                        for (commentaire clist1 : clist) {
                            SpanLabel com2 = new SpanLabel();
                            com2.setText(uname + ":  " + clist1.getComm());
                            com2.getAllStyles().setFgColor(0x00000);

                            ccom.add(com2);
                        }

                        f2.add(ccom);
                      
                           
                            //   n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound
                          

                     
                        f2.show();
                        f2.getToolbar().addCommandToLeftBar("back", null, (ev) -> {

                            f.show();
                        });
                    } catch (IOException ex) {
                    }
                }
            } //   lb.setText(s.getList2().toString());
            );

            f.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
             new ProfileForm(theme).show();
            });
            
        }
    }
    private com.codename1.io.MultipartRequest request;

    public void sendToNetwork(boolean timeElapsed, String file) throws IOException {
        request = new com.codename1.io.MultipartRequest() {

        };
        request.setUrl("http://localhost/SoukI/web/img");
        request.setPost(true);

        //add the data image
        request.addData("data", file, ".mp4");

        NetworkManager.getInstance().addToQueue(request);

    }
 private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(20);
        renderer.setLabelsColor(0xE12336);
        renderer.setLegendTextSize(0);
        
        renderer.setMargins(new int[]{0, 0, 0, 0});
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

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}

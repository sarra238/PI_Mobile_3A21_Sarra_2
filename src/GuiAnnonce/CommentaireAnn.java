/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiAnnonce;

import Entities.CommentAnn;
import Services.CommentairesAnnoncesServices;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;

public class CommentaireAnn {
    private Form f;
    private final  Resources theme;

    public CommentaireAnn() {
        theme = UIManager.initFirstTheme("/theme_1");
        f=new Form("Comentaire!",new FlowLayout(Component.CENTER, Component.CENTER));
        Container g= new Container(BoxLayout.y());
        Container c= new Container(BoxLayout.y());
        SpanLabel l = new SpanLabel("Veuillez laisser un commentaire");
        TextField t44= new TextField("","Commentaire",150,0);
        Button bAvis = new Button("Comment");
        bAvis.addActionListener((ActionListener) (ActionEvent evt1) -> {
            CommentairesAnnoncesServices ser = new CommentairesAnnoncesServices();
            CommentAnn t = new CommentAnn();
            t.setCommentAnn(t44.getText());
            ser.AjoutCom(t);  
            AuthMethod auth = new TokenAuthMethod("8bb4a8c1", "2exDUqcRWdpHptxh");  // (api_key,api_secret)
                        NexmoClient client = new NexmoClient(auth);
                        SmsSubmissionResult[] responses;
            try {
                responses = client.getSmsClient().submitMessage(new TextMessage(
                        "Sarra",
                        "+21623821291",
                        "Merci d'avoir laisser un commentaire !"));
                for (SmsSubmissionResult response : responses) {
                                System.out.println(response);
                            }
            } catch (IOException | NexmoClientException ex) {
               
            }
            CommentaireAnn com=new CommentaireAnn();
            com.getF().show();
        });
        c.add(l);
        c.add(t44);
        c.add(bAvis);
        g.add(c);
        Toolbar tb2= f.getToolbar();
        tb2.addCommandToLeftBar("Back",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                new AffichAnn2(theme).show();
        });
        CommentairesAnnoncesServices s =new CommentairesAnnoncesServices();
        ArrayList<CommentAnn> le = s.getList2();
        Container fk = new Container(BoxLayout.y());
        for (CommentAnn k :le)
        {
           Container cc = new Container(BoxLayout.y());
           SpanLabel ll = new SpanLabel(k.getCommentAnn());
           SpanLabel l2 = new SpanLabel(k.getDate());
           SpanLabel km=new SpanLabel("*************************************");
           cc.add(ll);
           cc.add(l2);
           cc.add(km);
           fk.add(cc);
        }
        SpanLabel kk=new SpanLabel("*************************************");
        g.add(kk);
        g.add(fk);
        f.add(g);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}

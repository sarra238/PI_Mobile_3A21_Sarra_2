

package com.codename1.uikit.materialscreens;

import Connexion.LoginForm;
import GuiAnnonce.AffichAnn2;
import GuiAnnonce.AffichAnnAllClt;
import GuiEvenement.ListEvent;
import GuiEvenement.evenementL;
import GuiProduitLivraison.Affichage;
import static Services.UserServices.role;
import static Services.UserServices.uname;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label( uname, profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Produit", FontImage.MATERIAL_DASHBOARD, e -> { 
            new Affichage(res).show();
            
        });
        getToolbar().addMaterialCommandToSideMenu("  Annonces", FontImage.MATERIAL_DASHBOARD,  e -> {
            new AffichAnn2(res).show();
        });
        if(role.equals("[ROLE_ARTISAN, ROLE_USER]")){
            getToolbar().addMaterialCommandToSideMenu("  mes Annonces", FontImage.MATERIAL_DASHBOARD,  en -> {
                AffichAnnAllClt ann= new AffichAnnAllClt();
                ann.getF().show();
        });}
        getToolbar().addMaterialCommandToSideMenu("  Evenement", FontImage.MATERIAL_ACCESS_TIME,  e -> {
            
            try {
                 new evenementL(res).show();
               
            } catch (IOException ex) {
                    }
            
        });
        if(role.equals("[ROLE_ARTISAN, ROLE_USER]")){
            getToolbar().addMaterialCommandToSideMenu("  mes evenement", FontImage.MATERIAL_DASHBOARD,  en -> {
                ListEvent l = new ListEvent();
                l.getF().show();
        });}
        getToolbar().addMaterialCommandToSideMenu("  Restoration", FontImage.MATERIAL_DASHBOARD,  e -> {
            GuiRestaurant.Affichage affichageRestaurant = new GuiRestaurant.Affichage();
            affichageRestaurant.getF().show();
        });
            getToolbar().addMaterialCommandToSideMenu("  Service Aprés Vente", FontImage.MATERIAL_SETTINGS,  e -> {
                GuiReclamation.AddReclamation affichageReclamation = new GuiReclamation.AddReclamation();
                affichageReclamation.getF().show();
            });
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
    }
    
    protected abstract void showOtherForm(Resources res);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

public class AvisAnnonce {
    private String avis;
    private Annonce AnnAv;



    public AvisAnnonce(String avis, Annonce AnnAv) {
        this.avis = avis;
        this.AnnAv = AnnAv;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public Annonce getAnnAv() {
        return AnnAv;
    }

    public void setAnnAv(Annonce AnnAv) {
        this.AnnAv = AnnAv;
    }
    
}

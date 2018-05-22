/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;


/**
 *
 * @author Win10
 */
public class Annonce {
    private int id;
    private String nomAnnonce ;
    private String description;
    private String type;
    private double prixReducton;
    private String image;

   public Annonce() {}

    public Annonce(String nomAnnonce, String description, String type, double prixReducton) {
        this.nomAnnonce = nomAnnonce;
        this.description = description;
        this.type = type;
        this.prixReducton = prixReducton;
    } 
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomAnnonce() {
        return nomAnnonce;
    }

    public void setNomAnnonce(String nomAnnonce) {
        this.nomAnnonce = nomAnnonce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrixReducton() {
        return prixReducton;
    }

    public void setPrixReducton(double prixReducton) {
        this.prixReducton = prixReducton;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Annonce other = (Annonce) obj;
        if (!Objects.equals(this.nomAnnonce, other.nomAnnonce)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return Objects.equals(this.prixReducton, other.prixReducton);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.nomAnnonce);
        hash = 73 * hash + Objects.hashCode(this.description);
        hash = 73 * hash + Objects.hashCode(this.type);
        hash = 73 * hash + Objects.hashCode(this.prixReducton);
        return hash;
    }

   

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", nomAnnonce=" + nomAnnonce + ", description=" + description + ", type=" + type + ", prixReducton=" + prixReducton + '}';
    }
    
    
    
    
}

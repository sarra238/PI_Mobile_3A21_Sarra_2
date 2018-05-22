/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.util.Objects;

/**
 *
 * @author lv
 */
public class Evenement {
    
      private int id;
      private String nomEvenement ;
      private String dateDeb;
      private String dateFin;
      private String Description;
      private String localisation;
      private String type;
      private String NomImg;
      private int userId;

    public Evenement( String nomEvenement, String dateDeb, String dateFin, String Description, String localisation ) {

        this.nomEvenement = nomEvenement;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.Description = Description;
        this.localisation = localisation;
    
       

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
     public Evenement( String nomEvenement, String Description, String localisation, String type) {
       
        this.nomEvenement = nomEvenement;
     
        this.Description = Description;
        this.localisation = localisation;
        this.type = type;
    }
   public Evenement(int id, String nomEvenement, String Description, String localisation, String type) {
        this.id = id;
        this.nomEvenement = nomEvenement;
        this.Description = Description;
        this.localisation = localisation;
        this.type = type;
    }
    public Evenement() {
    }

    public String getNomImg() {
        return NomImg;
    }

    public void setNomImg(String NomImg) {
        this.NomImg = NomImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.nomEvenement);
        hash = 79 * hash + Objects.hashCode(this.dateDeb);
        hash = 79 * hash + Objects.hashCode(this.dateFin);
        hash = 79 * hash + Objects.hashCode(this.Description);
        hash = 79 * hash + Objects.hashCode(this.localisation);
        hash = 79 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nomEvenement, other.nomEvenement)) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        if (!Objects.equals(this.localisation, other.localisation)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.dateDeb, other.dateDeb)) {
            return false;
        }
        return Objects.equals(this.dateFin, other.dateFin);
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nomEvenement=" + nomEvenement + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + ", Description=" + Description + ", localisation=" + localisation + ", type=" + type + '}';
    }
    
    
    
}

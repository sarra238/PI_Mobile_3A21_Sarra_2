/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

public class Commande {
     private int id;
     private int idProduits;
     private int etat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    

    public int getIdProduits() {
        return idProduits;
    }

    public void setIdProduits(int idProduits) {
        this.idProduits = idProduits;
    }
      public Commande() {
       
    }
     public Commande(int idProduits) {
     
        this.idProduits = idProduits;
    }
    

    public Commande(int id, int idProduits) {
        this.id = id;
        this.idProduits = idProduits;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", idProduits=" + idProduits + ", etat=" + etat + '}';
    }
    
      
      

   
    
     
     
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author maryem
 */
public class livraison {
private int id;
private int idProduits;
private int etat;

private String nom;
private String prenom;
private String adresse;
private int quantite;
private String nomProduit;

    public livraison() {
    }


    public livraison(int etat, String nom, String prenom) {
        this.etat = etat;
        this.nom = nom;
        this.prenom = prenom;
    }

    public livraison(int idProduits, int etat, String nom, String prenom, String adresse) {
        this.idProduits = idProduits;
        this.etat = etat;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public livraison(int id, int idProduits, int etat, String nom, String prenom, String adresse, int quantite ,String nomProduit) {
        this.id = id;
        this.idProduits = idProduits;
        this.etat = etat;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.quantite = quantite;
        this.nomProduit = nomProduit;
    }

  

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

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "livraison{" + "id=" + id + ", idProduits=" + idProduits + ", etat=" + etat + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", quantite=" + quantite + ", nomProduit=" + nomProduit + '}';
    }


    
}

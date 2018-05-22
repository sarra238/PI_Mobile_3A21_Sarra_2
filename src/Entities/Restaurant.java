/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Amine
 */
public class Restaurant {
    private int id;
    private String nom;
    private String adresse;
    private int numtel;
    private String categorie;
    private String valide;
    private float laitute;
    private float longtitude;
    private int idUser;

    public Restaurant() {
    }

    public Restaurant(int id, String nom, String adresse, int numtel, String categorie, String valide, float laitute, float longtitude, int idUser) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.numtel = numtel;
        this.categorie = categorie;
        this.valide = valide;
        this.laitute = laitute;
        this.longtitude = longtitude;
        this.idUser = idUser;
    }

    
    public String toString() {
        return nom +" "+ adresse +"\n" ;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getValide() {
        return valide;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    public float getLaitute() {
        return laitute;
    }

    public void setLaitute(float laitute) {
        this.laitute = laitute;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    

    
    
}

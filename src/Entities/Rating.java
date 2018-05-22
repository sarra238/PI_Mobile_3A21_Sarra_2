/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


/**
 *
 * @author BRAIKI Ahmad
 */
public class Rating {
    
    int note,idRestaurant;

    public Rating() {
    }

    public Rating(int note, int idRestaurant) {
        this.note = note;
        this.idRestaurant = idRestaurant;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
    
    
    
}

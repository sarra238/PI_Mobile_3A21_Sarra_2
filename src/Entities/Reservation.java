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
public class Reservation {
    private int id;
    private int id_user;
    private String date;
    private int Nbr;
    private int id_Resto;

    public Reservation() {
    }

    public Reservation(int id, int id_user, String date, int Nbr, int id_Resto) {
        this.id = id;
        this.id_user = id_user;
        this.date = date;
        this.Nbr = Nbr;
        this.id_Resto = id_Resto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNbr() {
        return Nbr;
    }

    public void setNbr(int Nbr) {
        this.Nbr = Nbr;
    }

    public int getId_Resto() {
        return id_Resto;
    }

    public void setId_Resto(int id_Resto) {
        this.id_Resto = id_Resto;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", id_user=" + id_user + ", date=" + date + ", Nbr=" + Nbr + ", id_Resto=" + id_Resto + '}';
    }
    
    
    
}

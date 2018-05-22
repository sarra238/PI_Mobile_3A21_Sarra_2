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
public class Reclamation {
    int id;
    String sujet;
    String msg;
    int idUser;

    public Reclamation() {
    }

    public Reclamation(int id, String sujet, String msg, int idUser) {
        this.id = id;
        this.sujet = sujet;
        this.msg = msg;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", sujet=" + sujet + ", msg=" + msg + ", idUser=" + idUser + '}';
    }
    
    
    
}

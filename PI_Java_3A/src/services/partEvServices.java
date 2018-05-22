/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.particEv;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.MyConnection;

/**
 *
 * @author lv
 */
public class partEvServices {
    
     private final Connection c = MyConnection.getInstance().getConnection();
     public void AjouterPart(particEv e) {
         
     
        PreparedStatement st;
        String query="insert into avis (type,idUser,idEvenement) values(?,?,?)";
        try {
            st= c.prepareStatement(query);
           
            st.setString(1,e.getType());
            
            st.setInt(2,e.getUserId());
            st.setInt(3,e.getIdEv());
            st.executeUpdate();
            System.out.println("Ajout accompli avec succés");
           
        } 
         catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout de l'evenement " + ex.getMessage());
        }
}


    public int Count (int idEv,int IdU){    
        int i =0; 
        try {
            PreparedStatement t ;
            String query= "Select * from avis where (idEvenement ='"+idEv+"') AND (idUser ='"+IdU+"')";
            t = c.prepareStatement(query);
            ResultSet rs = t.executeQuery();
            while (rs.next()){
                i+=1;   
            }
        } catch(SQLException ex) {
            System.out.println("erreur lors de la mise à jour de l'evenement " + ex.getMessage());
        }  
        return i;
    }


  public particEv RechercherParById(int idEv, int idU) {
        try {
            PreparedStatement pt;
            String query = "select type from avis where idEvenement='"+idEv+"' AND idUser='"+idU+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
        
            if (rs.first()) {
                particEv A=new particEv();
            
                A.setType(rs.getString(1));
                return A;
            }
        } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
        return null;
    }
  public int RechercherId(int idEv, int idU) {
        try {
            PreparedStatement pt;
            String query = "select id,type from avis where idEvenement='"+idEv+"' AND idUser='"+idU+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
        
            if (rs.first()) {
                particEv A=new particEv();
            A.setId(rs.getInt(1));
                A.setType(rs.getString(2));
                return A.getId();
            }
        } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
        return 0;
    }
    public void ModifierPart(particEv a) {
        try {
            PreparedStatement pt;
            String query = "update avis set type=? where id='"+a.getId()+"'";
            pt=c.prepareStatement(query);
           
            pt.setString(1,a.getType());
            pt.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour de l'evenement " + ex.getMessage());
        }     
    }
}

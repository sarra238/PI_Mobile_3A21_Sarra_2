/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.commentaire;
import Interfaces.commEvI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author lv
 */
public class commServices extends commEvI{
 private final Connection c = MyConnection.getInstance().getConnection();
    @Override
    public void AjouterComm(commentaire e) {
            

       PreparedStatement st;
        String query="insert into com_event (commentaire,idUser,idEvenement) values(?,?,?)";
        try {
            st= c.prepareStatement(query);
           
            st.setString(1,e.getComm());
            
            st.setInt(2,e.getUserId());
            st.setInt(3,e.getIdEv());
            st.executeUpdate();
            System.out.println("Ajout accompli avec succés");
           
        } 
         catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout de l'evenement " + ex.getMessage());
        } }

    @Override
    public List<commentaire> AfficherAllComm( int ide ) {
       List<commentaire> Ann= new ArrayList<>();
        String query="select  commentaire from  com_event where idEvenement='"+ide+"' ";
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {
                commentaire A=new commentaire();
            
                 A.setComm(rs.getString(1));
               
               
             
                Ann.add(A);
            }
            return Ann;
        } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de tous les evenements! " + ex.getMessage());
        }
        return null;  }

 
   
}

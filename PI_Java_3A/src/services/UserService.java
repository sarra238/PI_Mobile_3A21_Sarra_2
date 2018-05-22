/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.User;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
import utils.MyConnection;
import utils.crypt;

public class UserService {
   public static int conn ; 
    private final Connection c = MyConnection.getInstance().getConnection();
    
    public String login(User u) {
        String f="hi";
        try {
            String loginQry = "SELECT * FROM user WHERE username = ? AND password= ?";
            PreparedStatement ste;
            ste = c.prepareStatement(loginQry);
            ste.setString(1, u.getUsername());
 
            
            ste.setString(2, u.getPassword());
            ResultSet rs = ste.executeQuery();
          //  System.out.println("erreur lors de la connexion ");
         
            while(rs.next()){
                System.out.println("Connexion accomplie");
                f=rs.getString("roles");
                conn=rs.getInt("id");
        } 
                }
          
        catch (SQLException ex) {
            System.out.println("erreur lors de la connexion " + ex.getMessage());
        }
        return f;
    }
    public void signIn(User u) 
    {
        try {
            String req = "INSERT INTO `user`(`username`,`email`, `password`, `nom`,`prenom` ,`roles`,  `adresse`,`numTel`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ste;
            ste= c.prepareStatement(req);
            ste.setString(1, u.getUsername());
               ste.setString(2, u.getEmail());
        String m =u.getPassword(); 
          
           // String n =c.get_SHA_512_SecurePassword(m ,salt);
           String hashed = BCrypt.hashpw(m, BCrypt.gensalt());

            ste.setString(3,hashed );
            ste.setString(4, u.getFname());
            ste.setString(5, u.getLname());
         
        
            ste.setString(6, u.getRole());
   
            ste.setString(7, u.getAddress());
             ste.setInt(8, u.getPhoneNumber());
            ste.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de l'inscription " + ex.getMessage());
        }
    }
    public boolean MdpO(User u) 
    {
        PreparedStatement pt;  
        boolean b=false;
        try{
            String query = "update user set password=? where username='"+u.getUsername()+"'and email='"+u.getEmail()+"'";
            pt=c.prepareStatement(query);
            pt.setString(1,u.getPassword());
            pt.executeUpdate();
            b=true;
        
        }
         catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour du mot de passe oublié " + ex.getMessage());
        }
        return b;
    }
     public User RechercherUsertById(int id)  {
        try {
            PreparedStatement pt;
            String query = "select id,username,email, password, nom, prenom ,roles,  adresse,numTel from user where id='"+id+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
        
            if (rs.next()) {
                User A=new User();
                A.setId(rs.getInt(1));
                A.setUsername(rs.getString(2));
                 A.setEmail(rs.getString(3));
                A.setPassword(rs.getString(4));
                A.setFname(rs.getString(5));
                A.setLname(rs.getString(6));
                A.setRole(rs.getString(6));
                
               A.setAddress(rs.getString(7));
                 A.setRole(rs.getString(8));
                 A.setPhoneNumber(rs.getInt(9));
                   
                     
                return A;
            }}
            catch (SQLException ex) {
                System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
        return null;
        

     
     
     
     }
      public String RechercherUsertByPass(String p) throws SQLException {
        try {
            PreparedStatement pt;
            String query = "select password from user where username='"+p+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
        String res;
            if (rs.next()) {
               
                
                res=rs.getString(1);
               
                return res;
            }}
            catch (SQLException ex) {
             //   System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
        return null;
        

     
     
     
     }
        /*public String RechercherUsertByName(String p) throws SQLException {
        try {
            PreparedStatement pt;
            String query = "select password from user where username='"+p+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
        String res;
            if (rs.next()) {
               
                
                res=rs.getString(1);
               
                return res;
            }}
            catch (SQLException ex) {
               // System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
        return null;
        

     
     
     
     }*/


}

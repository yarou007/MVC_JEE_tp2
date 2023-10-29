
package controleurs;

import java.io.Serializable;
import java.sql.*;
import java.util.*;

import modele.*;

public class Service implements Serializable {
    
    private String url;
    private String login;
    private String pass;
    
    private Connection cx; // outils 
    
    public Service(String url, String login, String pass) {
        this.url = url;
        this.login = login;
        this.pass = pass;
    }
    
    public boolean connexion(){
        try{
            //Chargement du driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connexion avec la base de données
            cx = DriverManager.getConnection(url, login, pass);
            return true;
        }catch(ClassNotFoundException | SQLException ex){
            return false;
        }
    }
    
    public boolean deconnexion(){
        try {
            //Fermeture de la connexion
            cx.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean isExistant(Etudiant e){
        try {
            //Ecriture d'une requête
            String sql="SELECT * FROM `etudiant` WHERE `id`=?";
            //Création de la requête
            PreparedStatement stm = cx.prepareStatement(sql);
            //Injection des paramètres manquants
            stm.setInt(1, e.getId());
            //Execution de la requête
            ResultSet res =  stm.executeQuery();
            if(res.next())
                return true;
            else
                return false;
        } catch (SQLException ex) {
            return false;
        }
    }
    public int insertion(Etudiant e){
        try {
            //Ecriture d'une requête
            String sql="INSERT INTO `etudiant`(`id`, `nom`, `prenom`) " + "VALUES (?,?,?)";
            //Création de la requête
            PreparedStatement stm = cx.prepareStatement(sql);
            //Injection des paramètres manquants
            stm.setInt(1, e.getId());
            stm.setString(2, e.getNom());
            stm.setString(3, e.getPrenom());
            //Execution de la requête
            return stm.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
        
    }
    
    public List<Etudiant> ListeDesEtudiant(){
         try {
            //Ecriture d'une requête
            String sql="SELECT * FROM `etudiant`";
            //Création de la requête
            Statement stm = cx.createStatement();
            //Execution de la requête
            ResultSet res =  stm.executeQuery(sql);
            //Creation d'une liste
            List<Etudiant> l = new ArrayList<Etudiant>(); // polymorphisme
            //Parcours de la table Etudiant
            while(res.next()){
                l.add(new Etudiant(res.getInt("id"),
                                   res.getString("nom"),
                                   res.getString("prenom"))
                     );
            }
            res.close();
            return l;

        } catch (SQLException ex) {
            return null;
        }       
    }
    
    
    
    
    
    
}

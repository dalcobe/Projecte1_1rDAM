package aplicacio.basedades;

import aplicacio.model.Compte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompteDAO {

    public ArrayList<Compte> obtenirComptesModalitatBD(int id_modalitat) {
        Connection conn = Connexio.getConnection();
        
        ArrayList<Compte> comptes = new ArrayList();
        
        String sentenciaSql = null;
        
        if (id_modalitat==0) {
            sentenciaSql= "SELECT co.id_compte, cl.id_client, cl.nom, cl.DNI, co.id_modalitat FROM compte co, client cl where co.id_client = cl.id_client";
        }else{
            sentenciaSql = "SELECT co.id_compte, cl.id_client, cl.nom, cl.DNI, co.id_modalitat FROM compte co, client cl where co.id_modalitat = ? and co.id_client = cl.id_client";  
            }
        
        try (PreparedStatement ps = conn.prepareStatement(sentenciaSql)){
            if (id_modalitat != 0) ps.setInt(1, id_modalitat);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Compte c = new Compte();
                c.setIdCompte(rs.getInt("co.id_compte"));
                c.setIdClient(rs.getInt("cl.id_client"));
                c.setDNI(rs.getString("cl.DNI"));
                //c.setDataAlta(rs.getDate("co.data_alta").toLocalDate());
                c.setNomCli(rs.getString("cl.nom"));
                c.setIdModalitat(rs.getInt("co.id_modalitat"));
                comptes.add(c);
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return comptes;
    }
    
    
}

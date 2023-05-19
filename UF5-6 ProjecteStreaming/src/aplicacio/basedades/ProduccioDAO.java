package aplicacio.basedades;

import aplicacio.model.Produccions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class ProduccioDAO {
    public Produccions consultaProduccioBD(int id){
        Connection conn = Connexio.getConnection();
        Produccions produccio = null;
        String sentenciaSql = "SELECT id_produccio, nom, nacionalitat, any, favorit " + "FROM produccions where id_produccio = ?";
        
        try(PreparedStatement ps = conn.prepareStatement(sentenciaSql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                produccio = dadesBDProduccio(rs);
            }
        } catch(SQLException sqle){
            sqle.printStackTrace();
        }
        
        return produccio;
    }
    
//    public void altaProduccioBD(Produccions p){};
//    
//    public void modificarProduccioBD(Produccions p){};
//    
//    public void baixaProduccioBD(Produccions p){};
    
//    public Collection<Produccions> obtenirTotesProduccionsBD(){
//        return null;
//    }
    
    private Produccions dadesBDProduccio(ResultSet rs) throws SQLException{
        Produccions p = new Produccions();
        
        p.setId(rs.getInt("id_produccio"));
        p.setNom(rs.getString("nom"));
        p.setNacionalitat(rs.getString("nacionalitat"));
        p.setAny(rs.getInt("any"));
        p.setFavorit(rs.getInt("favorit"));
        
        return p;
    }
    
    private void dadesProduccioBD(PreparedStatement ps, Produccions p) throws SQLException{
        ps.setInt(1, p.getId());
        ps.setString(2, p.getNom());
        ps.setString(3, p.getNacionalitat());
        ps.setInt(4, p.getAny());
        ps.setInt(5, p.getFavorit());
    }
    
    
}

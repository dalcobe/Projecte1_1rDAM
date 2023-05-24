package aplicacio.basedades;

import aplicacio.model.Series;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SerieDAO {
    public Series consultaSerieBD(int id) {
        
        Connection conn = Connexio.getConnection();
        
        Series s = null;
        
        String sentenciaSql = "SELECT COUNT(e.id_produccio), SUM(e.durada) as duradatotal FROM series s, episodi e WHERE s.id_produccio = ? AND s.id_produccio = e.id_produccio";
        
        try (PreparedStatement ps = conn.prepareStatement(sentenciaSql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s=dadesBDSerie(id,rs);
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        
        return s;
        
    }
    
    private Series dadesBDSerie(int id, ResultSet rs) throws SQLException{
        Series s = new Series();
        s.setId(id);
        s.setDuradaTotal(rs.getDouble("durada"));
        //s.setNumCapitols(rs.getInt("num"));
        
        s.afegirActor(obtenirActor(id));
        s.afegirCategoria(obtenirCategoria(id));
        s.afegirDirector(obtenirDirector(id));
        
        return s;
    }
    
    private String obtenirDirector(int idProduccio) {
        Connection conn = Connexio.getConnection();
        
        String director = " ";
        
        String sentenciaSql = "SELECT d.nomdirector FROM director d, dirigeix_episodi de WHERE d.id_director = ? and de.id_director = d.id_director";
        
        try(PreparedStatement ps = conn.prepareStatement(sentenciaSql)){
            ps.setInt(1, idProduccio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nomdirector");
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return director;
    }
    
    private String obtenirCategoria(int idProduccio) {
        Connection conn = Connexio.getConnection();
        
        String categoria = " ";
        
        String sentenciaSql = "SELECT nom FROM pertany pert, genere gen WHERE pert.id_produccio = ? AND pert.id_categoria = gen.id_categoria";
        
        try(PreparedStatement ps = conn.prepareStatement(sentenciaSql)){
            ps.setInt(1, idProduccio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nom");
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return categoria;
    }
    
    private String obtenirActor(int idProduccio) {
        Connection conn = Connexio.getConnection();
        
        String actor = " ";
        
        String sentenciaSql = "SELECT nomactor FROM actuen actu, actors acto WHERE actu.id_produccio = ? AND actu.id_actor = acto.id_actor";
        
        try(PreparedStatement ps = conn.prepareStatement(sentenciaSql)){
            ps.setInt(1, idProduccio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nomactor");
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return actor;
    }
    
}

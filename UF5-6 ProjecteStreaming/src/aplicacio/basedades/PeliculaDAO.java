package aplicacio.basedades;

import aplicacio.model.Pelicula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeliculaDAO {
    public Pelicula consultaPelicuaBD(int id){
        Connection conn = Connexio.getConnection();
        Pelicula p = null;
        
        String sentenciaSql = "SELECT durada FROM pelicules WHERE id_produccio=?";
        
        try(PreparedStatement ps = conn.prepareStatement(sentenciaSql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p=dadesBDPelicula(id,rs.getDouble("durada"));
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        
        return p;
    }
    
    private Pelicula dadesBDPelicula(int id, double durada) {
        Pelicula p = new Pelicula();
        
        p.setDurada(durada);
        
        p.afegirCategoria(obtenirCategoria(id));
        p.afegirDirector(obtenirDirector(id));
        p.afegirActor(obtenirActor(id));
        
        return p;
    }
    
    private String obtenirDirector(int idProduccio)  {
        Connection conn = Connexio.getConnection();
        
        String director = " ";
        
        String sentenciaSql = "SELECT d.nomdirector FROM director d, dirigeix_pelicula dp WHERE d.id_director = ? and dp.id_director = d.id_director";
        
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
        
        String sentenciaSql = "SELECT gen.nom FROM pertany pert, genere gen WHERE pert.id_produccio = ? AND pert.id_categoria = gen.id_categoria";
        
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

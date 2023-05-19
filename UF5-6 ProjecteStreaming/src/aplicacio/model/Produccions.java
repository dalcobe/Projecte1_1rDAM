package aplicacio.model;

public class Produccions {
    private int id;
    private String nom;
    private String nacionalitat;
    private int any;
    private int favorit;

    public Produccions() {
    }

    public Produccions(String nom, int any, String genere, String categoria, String director, String actor) {
        this.nom = nom;
        this.any = any;
    }

    public Produccions(int id, String nom, int any, String genere, String categoria, String director, String actor) {
        this.id = id;
        this.nom = nom;
        this.any = any;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNacionalitat() {
        return nacionalitat;
    }

    public void setNacionalitat(String nacionalitat) {
        this.nacionalitat = nacionalitat;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public int getFavorit() {
        return favorit;
    }

    public void setFavorit(int favorit) {
        this.favorit = favorit;
    }
    
     
    
}

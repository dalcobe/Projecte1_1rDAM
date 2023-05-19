package aplicacio.model;

import java.util.ArrayList;

public class Series extends Produccions {

    private int numCapitols;
    private double duradaTotal;

    private ArrayList<String> categories;
    private ArrayList<String> directors;
    private ArrayList<String> actors;

    public Series() {
        this.categories = new ArrayList();
        this.directors = new ArrayList();
        this.actors = new ArrayList();
    }

    public Series(int numCapitols, double duradaTotal, ArrayList<String> categories, ArrayList<String> directors, ArrayList<String> actors, String nom, int any, String genere, String categoria, String director, String actor) {
        super(nom, any, genere, categoria, director, actor);
        this.numCapitols = numCapitols;
        this.duradaTotal = duradaTotal;
        this.categories = new ArrayList();
        this.directors = new ArrayList();
        this.actors = new ArrayList();
    }

    public void afegirDirector(String director) {
        this.directors.add(director);
    }

    public void afegirActor(String actor) {
        this.actors.add(actor);
    }

    public void afegirCategoria(String categoria) {
        this.categories.add(categoria);
    }

    public String getDirector(int pos) {
        if (pos <= directors.size() - 1) {
            return this.directors.get(pos);
        }
        return "";
    }

    public String getCategoria(int pos) {
        if (pos <= categories.size() - 1) {
            return this.categories.get(pos);
        }
        return "";
    }

    public String getActor(int pos) {
        if (pos <= actors.size() - 1) {
            return this.actors.get(pos);
        }
        return "";
    }

    public void setNumCapitols(int numCapitols) {
        this.numCapitols = numCapitols;
    }

    public void setDuradaTotal(double duradaTotal) {
        this.duradaTotal = duradaTotal;
    }

    public int getNumCapitols() {
        return numCapitols;
    }

    public double getDuradaTotal() {
        return duradaTotal;
    }

}

package main.Models;

public class Specialite {

    private String intitule;
    private int id, capacite;

    public Specialite() {
    }

    public Specialite(String intitule, int id, int capacite) {
        this.intitule = intitule;
        this.id = id;
        this.capacite = capacite;
    }

    public Specialite(String intitule, String niveau, int capacite) {
        this.intitule = intitule;
        this.capacite = capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

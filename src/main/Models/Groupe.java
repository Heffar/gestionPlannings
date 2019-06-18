package main.Models;

public class Groupe {

    private String intitule;
    private int id, capacite, idSection;

    public Groupe() {
    }

    public Groupe(String intitule, int id, int capacite, int idSection) {
        this.intitule = intitule;
        this.id = id;
        this.capacite = capacite;
        this.idSection = idSection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getIdSection() {
        return idSection;
    }

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }
}

package main.Models;

public class Section {

    private String intitule, niveau;
    private int id, idSpecialite, capacite;

    public Section() {
    }

    public Section(String intitule, String niveau, int id, int idSpecialite, int capacite) {
        this.intitule = intitule;
        this.niveau = niveau;
        this.id = id;
        this.idSpecialite = idSpecialite;
        this.capacite = capacite;
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

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(int idSpecialite) {
        this.idSpecialite = idSpecialite;
    }
}

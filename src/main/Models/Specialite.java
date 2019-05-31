package main.Models;

public class Specialite {

    private String titre, niveau;
    private int capacite;

    public Specialite() {
    }

    public Specialite(String titre, String niveau, int capacite) {
        this.titre = titre;
        this.niveau = niveau;
        this.capacite = capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}

package main.Models;

public class Section {

    private String titre, niveau, specialite;

    public Section() {
    }

    public Section(String titre, String niveau, String specialite) {
        this.titre = titre;
        this.niveau = niveau;
        this.specialite = specialite;
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

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}

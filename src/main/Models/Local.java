package main.Models;

public class Local  {

    private String titre;
    private int capacite;
    private boolean typeCours, typeTD, typeTP;

    public Local() {
    }

    public Local(String titre, int capacite, boolean typeCours, boolean typeTD, boolean typeTP) {
        this.titre = titre;
        this.capacite = capacite;
        this.typeCours = typeCours;
        this.typeTD = typeTD;
        this.typeTP = typeTP;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public boolean isTypeCours() {
        return typeCours;
    }

    public void setTypeCours(boolean typeCours) {
        this.typeCours = typeCours;
    }

    public boolean isTypeTD() {
        return typeTD;
    }

    public void setTypeTD(boolean typeTD) {
        this.typeTD = typeTD;
    }

    public boolean isTypeTP() {
        return typeTP;
    }

    public void setTypeTP(boolean typeTP) {
        this.typeTP = typeTP;
    }
}
